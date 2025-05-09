package data

import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MuseumRepository(private val api: MuseumApi) : Repository {
    override suspend fun initialize() {
        Napier.e("MuseumRepository - initialize")
    }

    override suspend fun refresh() {
        Napier.e("MuseumRepository - refresh")
    }

    override suspend fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        Napier.e("MuseumRepository - getObjectById: $objectId")
        return getObjects().let { result ->
            when (result) {
                is NetworkResult.Success -> result.data.map { objects ->
                    objects.find { it.objectID == objectId }
                }
                else -> flow { emit(null) }
            }
        }
    }

    override suspend fun getObjects(): NetworkResult<Flow<List<MuseumObject>>> {
        return try {
            Napier.e("MuseumRepository - getObjects - Iniciando busca")
            val objects = api.getData()
            Napier.e("MuseumRepository - getObjects - Sucesso: ${objects.size} objetos encontrados")
            NetworkResult.Success(flow { emit(objects) })
        } catch (e: Exception) {
            Napier.e("MuseumRepository - getObjects - Error: $e")
            NetworkResult.Exception(e)
        }
    }
} 