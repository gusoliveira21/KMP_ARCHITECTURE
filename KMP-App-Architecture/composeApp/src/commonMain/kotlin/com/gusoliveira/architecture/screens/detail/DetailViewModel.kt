package com.gusoliveira.architecture.screens.detail

import androidx.lifecycle.ViewModel
import domain.model.MuseumObject
import domain.repository.Repository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow

class DetailViewModel(
    private val repository: Repository
) : ViewModel() {
    suspend fun getObject(objectId: Int): Flow<MuseumObject?> {
        Napier.e("DetailViewModel - getObject: $objectId")
        return repository.getObjectById(objectId)
    }
}
