package com.gusoliveira.architecture.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MuseumRepository(
    private val IMuseumApi: IMuseumApi,
    private val IMuseumStorage: IMuseumStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        IMuseumStorage.saveObjects(IMuseumApi.getData())
    }

    fun getObjects(): Flow<List<MuseumObject>> = IMuseumStorage.getObjects()

    fun getObjectById(objectId: Int): Flow<MuseumObject?> = IMuseumStorage.getObjectById(objectId)
}
