package data

import domain.model.MuseumObject
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface MuseumStorage {
    suspend fun saveObjects(newObjects: List<MuseumObject>)

    fun getObjectById(objectId: Int): Flow<MuseumObject?>

    fun getObjects(): Flow<List<MuseumObject>>
}

class InMemoryMuseumStorage : MuseumStorage {
    private val storedObjects = MutableStateFlow(emptyList<MuseumObject>())

    override suspend fun saveObjects(newObjects: List<MuseumObject>) {
        Napier.e("MuseumStorage - saveObjects - Salvando ${newObjects.size} objetos")
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        Napier.e("MuseumStorage - getObjectById: $objectId")
        return storedObjects.map { objects ->
            objects.find { it.objectID == objectId }
        }
    }

    override fun getObjects(): Flow<List<MuseumObject>> {
        Napier.e("MuseumStorage - getObjects - Retornando ${storedObjects.value.size} objetos")
        return storedObjects
    }

    fun clear() {
        Napier.e("MuseumStorage - clear - Limpando storage")
        storedObjects.value = emptyList()
    }
}
