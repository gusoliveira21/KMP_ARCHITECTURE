package com.gusoliveira.architecture.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gusoliveira.architecture.data.MuseumObject
import com.gusoliveira.architecture.data.MuseumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: MuseumRepository
) : ViewModel() {
    private val _objects = MutableStateFlow<List<MuseumObject>>(emptyList())
    val objects: StateFlow<List<MuseumObject>> = _objects.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getObjects().collect { objects ->
                _objects.value = objects
            }
        }
    }
}
