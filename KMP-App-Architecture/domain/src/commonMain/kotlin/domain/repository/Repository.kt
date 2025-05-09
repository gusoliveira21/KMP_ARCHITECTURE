package domain.repository

import domain.model.MuseumObject
import domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun initialize()
    suspend fun refresh()
    suspend fun getObjects(): NetworkResult<Flow<List<MuseumObject>>>
    suspend fun getObjectById(objectId: Int): Flow<MuseumObject?>
}