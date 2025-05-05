package com.gusoliveira.architecture.screens.detail

import androidx.lifecycle.ViewModel
import com.gusoliveira.architecture.data.MuseumObject
import com.gusoliveira.architecture.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
