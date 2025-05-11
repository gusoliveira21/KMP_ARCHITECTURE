package domain.repository

import domain.model.MuseumObject
import domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getObjectsFlow(): Flow<List<MuseumObject>>
    suspend fun getObjects(): NetworkResult<List<MuseumObject>>
    suspend fun getObjectById(objectId: Int): Flow<MuseumObject?>
}