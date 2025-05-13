package com.gusoliveira.architecture.data

import kotlinx.coroutines.flow.Flow

interface IMuseumStorage {
    suspend fun saveObjects(newObjects: List<MuseumObject>)

    fun getObjectById(objectId: Int): Flow<MuseumObject?>

    fun getObjects(): Flow<List<MuseumObject>>
}
