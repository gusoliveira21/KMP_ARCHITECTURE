package domain.repository

import domain.model.MuseumObject
import domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getObjectsFlow(): NetworkResult<Flow<List<MuseumObject>>>
    suspend fun getObjectById(objectId: Int): NetworkResult<Flow<MuseumObject?>>
}