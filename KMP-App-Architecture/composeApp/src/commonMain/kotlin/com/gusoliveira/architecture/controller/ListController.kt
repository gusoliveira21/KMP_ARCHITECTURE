package com.gusoliveira.architecture.controller

import com.gusoliveira.architecture.data.MuseumObject
import com.gusoliveira.architecture.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class ListController(private val repository: MuseumRepository) {
    fun getObjects(): Flow<List<MuseumObject>> = repository.getObjects()
}