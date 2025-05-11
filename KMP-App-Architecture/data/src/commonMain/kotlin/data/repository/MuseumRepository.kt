package data.repository

import data.api.MuseumApi
import data.Storage.MuseumStorage
import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
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
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() : Unit {
        try {
            val newObjects = museumApi.getData()
            museumStorage.saveObjects(newObjects)
            _objectsFlow.value = newObjects
        } catch (e: Exception) {
            // Em caso de erro, tenta recuperar do storage
            val storedObjects = museumStorage.getObjects()
            _objectsFlow.value = storedObjects
        }
    }

    override suspend fun getObjects() : NetworkResult<List<MuseumObject>> {
        return try {
            val newObjects = museumApi.getData()
            museumStorage.saveObjects(newObjects)
            _objectsFlow.value = newObjects
            
            NetworkResult.Success(newObjects)
        } catch (e: Exception) {
            val storedObjects = museumStorage.getObjects()
            _objectsFlow.value = storedObjects
            NetworkResult.Success(storedObjects)
        }
    }

    override fun getObjectsFlow(): Flow<List<MuseumObject>> {
        // Se o flow estiver vazio, tenta carregar do storage
        if (_objectsFlow.value.isEmpty()) {
            scope.launch {
                val storedObjects = museumStorage.getObjects()
                if (storedObjects.isNotEmpty()) {
                    _objectsFlow.value = storedObjects
                } else {
                    // Se não houver dados no storage, tenta buscar da API
                    refresh()
                }
            }
        }
        return _objectsFlow.asStateFlow()
    }

    override suspend fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        return museumStorage.getObjectById(objectId)
    }
}
