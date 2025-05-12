package com.gusoliveira.architecture.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MuseumObject
import domain.usercase.get.GetObjectsFlowUserCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val userCase: GetObjectsFlowUserCase
) : ViewModel() {
    private val _objects = MutableStateFlow<List<MuseumObject>>(emptyList())
    val objects: StateFlow<List<MuseumObject>> = _objects.asStateFlow()

    init { 
        loadObjects()
    }

    private fun loadObjects() {
        viewModelScope.launch {
            userCase.execute(Unit).handleResult(
                success = { flow ->
                    launch {
                        flow.collect { museumObjects ->
                            _objects.value = museumObjects
                        }
                    }
                },
                error = { throwable ->
                    if (throwable != null) {
                        Napier.e("ListViewModel - Erro ao carregar objetos: ${throwable.message}")
                    }
                    _objects.value = emptyList()
                }
            )
        }
    }
} 