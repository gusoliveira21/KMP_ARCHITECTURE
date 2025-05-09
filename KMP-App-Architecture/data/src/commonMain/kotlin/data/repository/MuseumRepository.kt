package data.repository

import data.MuseumApi
import data.MuseumStorage
import domain.model.MuseumObject
import domain.network.NetworkResult
import domain.repository.Repository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class MuseumRepository(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
): Repository{
    private val scope = CoroutineScope(SupervisorJob())

    override suspend fun initialize() : Unit {
        Napier.e("MuseumRepository - initialize")
        refresh()
    }

    override suspend fun refresh() : Unit {
        Napier.e("MuseumRepository - refresh")
        museumStorage.saveObjects(museumApi.getData())
    }

    override suspend fun getObjects() : NetworkResult<Flow<List<MuseumObject>>> {
        Napier.e("MuseumRepository - getObjects")
        return NetworkResult.Success(museumStorage.getObjects())
    }

    override suspend fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        Napier.e("MuseumRepository - getObjectById: $objectId")
        return museumStorage.getObjectById(objectId)
    }
}
