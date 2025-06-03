package com.gusoliveira.architecture.controller

import com.gusoliveira.architecture.data.MuseumObject
import com.gusoliveira.architecture.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailController(private val repository: MuseumRepository) {
    fun getObjectById(objectId: Int): Flow<MuseumObject?> = repository.getObjectById(objectId)
}