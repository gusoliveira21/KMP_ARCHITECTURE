package data.repository

import data.api.MuseumApi
import data.Storage.MuseumStorage
import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MuseumRepository(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
): Repository{
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() : Unit {
        scope.launch {
            refresh()
        }
    }

     private suspend fun refresh() : Unit {
        museumStorage.saveObjects(museumApi.getData())
    }

    override suspend fun getObjects() : NetworkResult<List<MuseumObject>> {
        return NetworkResult.Success(museumStorage.getObjects())
    }

    override suspend fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        return museumStorage.getObjectById(objectId)
    }
}
