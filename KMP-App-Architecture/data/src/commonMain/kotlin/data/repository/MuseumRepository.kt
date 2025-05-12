package data.repository

import data.api.MuseumApi
import data.Storage.MuseumStorage
import domain.model.MuseumObject
import domain.network.ErrorBody
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
            val storedObjects = museumStorage.getObjects()
            _objectsFlow.value = storedObjects
        }
    }

    override fun getObjectsFlow(): NetworkResult<Flow<List<MuseumObject>>> {
        if (_objectsFlow.value.isEmpty()) {
            scope.launch {
                val storedObjects = museumStorage.getObjects()
                if (storedObjects.isNotEmpty()) {
                    _objectsFlow.value = storedObjects
                } else {
                    refresh()
                }
            }
        }
        return NetworkResult.Success(_objectsFlow.asStateFlow())
    }

    override suspend fun getObjectById(objectId: Int): NetworkResult<Flow<MuseumObject?>> {

        return try {
            val obj = museumStorage.getObjectById(objectId)
            NetworkResult.Success(obj)
        } catch (e: Exception) {
            NetworkResult.Exception(e)
        }
        catch (e: Error) {
            NetworkResult.Error(e.hashCode(), e.message?.let { ErrorBody(it) })
        }
    }
}
