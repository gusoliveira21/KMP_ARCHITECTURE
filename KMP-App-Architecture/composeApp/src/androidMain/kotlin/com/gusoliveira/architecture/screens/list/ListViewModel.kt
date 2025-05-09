package com.gusoliveira.architecture.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MuseumObject
import domain.usercase.get.GetData
import timber.log.Timber
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val userCase: GetData
) : ViewModel() {
    private val _objects = MutableStateFlow<List<MuseumObject>>(emptyList())
    val objects: StateFlow<List<MuseumObject>> = _objects.asStateFlow()

    init {
        Timber.d("ListViewModel - init")
        loadObjects()
    }

    private fun loadObjects() {
        Timber.d("ListViewModel - loadObjects")
        viewModelScope.launch {
            userCase.execute(Unit).handleResult(
                success = { flow: Flow<List<MuseumObject>> ->
                    launch {
                        flow.collect { museumObjects ->
                            Timber.d("ListViewModel - Objects loaded: ${museumObjects.size}")
                            _objects.value = museumObjects
                        }
                    }
                },
                error = { throwable ->
                    Timber.e(throwable, "ListViewModel - Error loading objects")
                    _objects.value = emptyList()
                }
            )
        }
    }
} 