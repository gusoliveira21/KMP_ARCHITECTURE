package data.repository

import data.api.MuseumApi
import data.Storage.MuseumStorage
import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MuseumRepository(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
): Repository {
    private val scope = CoroutineScope(SupervisorJob())
    private val _objectsFlow = MutableStateFlow<List<MuseumObject>>(emptyList())

    fun initialize() : Unit {
        Napier.e("MuseumRepository - Inicializando repository")
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() : Unit {
        Napier.e("MuseumRepository - Iniciando refresh")
        try {
            val newObjects = museumApi.getData()
            Napier.e("MuseumRepository - Dados recebidos da API: ${newObjects.size} objetos")
            museumStorage.saveObjects(newObjects)
            _objectsFlow.value = newObjects
            Napier.e("MuseumRepository - Dados salvos no storage e flow atualizado")
        } catch (e: Exception) {
            Napier.e("MuseumRepository - Erro no refresh: ${e.message}")
            // Em caso de erro, tenta recuperar do storage
            val storedObjects = museumStorage.getObjects()
            _objectsFlow.value = storedObjects
            Napier.e("MuseumRepository - Recuperando dados do storage: ${storedObjects.size} objetos")
        }
    }

    override suspend fun getObjects() : NetworkResult<List<MuseumObject>> {
        Napier.e("MuseumRepository - getObjects chamado")
        return try {
            val newObjects = museumApi.getData()
            Napier.e("MuseumRepository - Dados recebidos da API em getObjects: ${newObjects.size} objetos")
            museumStorage.saveObjects(newObjects)
            _objectsFlow.value = newObjects
            Napier.e("MuseumRepository - Dados salvos no storage e flow atualizado em getObjects")
            
            NetworkResult.Success(newObjects)
        } catch (e: Exception) {
            Napier.e("MuseumRepository - Erro em getObjects: ${e.message}")
            val storedObjects = museumStorage.getObjects()
            _objectsFlow.value = storedObjects
            Napier.e("MuseumRepository - Recuperando dados do storage em getObjects: ${storedObjects.size} objetos")
            NetworkResult.Success(storedObjects)
        }
    }

    override fun getObjectsFlow(): Flow<List<MuseumObject>> {
        Napier.e("MuseumRepository - getObjectsFlow chamado")
        // Se o flow estiver vazio, tenta carregar do storage
        if (_objectsFlow.value.isEmpty()) {
            scope.launch {
                val storedObjects = museumStorage.getObjects()
                if (storedObjects.isNotEmpty()) {
                    _objectsFlow.value = storedObjects
                    Napier.e("MuseumRepository - Dados carregados do storage para o flow: ${storedObjects.size} objetos")
                } else {
                    // Se não houver dados no storage, tenta buscar da API
                    refresh()
                }
            }
        }
        return _objectsFlow.asStateFlow()
    }

    override suspend fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        Napier.e("MuseumRepository - getObjectById chamado para ID: $objectId")
        return museumStorage.getObjectById(objectId)
    }
}
