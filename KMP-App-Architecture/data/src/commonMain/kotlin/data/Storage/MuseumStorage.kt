package data.Storage

import domain.model.MuseumObject
import kotlinx.coroutines.flow.Flow

interface MuseumStorage {
    suspend fun saveObjects(newObjects: List<MuseumObject>)
    fun getObjects(): List<MuseumObject>
    fun getObjectById(objectId: Int): Flow<MuseumObject?>
}