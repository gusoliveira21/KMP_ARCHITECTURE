package com.gusoliveira.architecture.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MuseumObject
import domain.usercase.get.GetData
import io.github.aakira.napier.Napier
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
        Napier.e("ListViewModel - Inicializando ViewModel")
        loadObjects() 
    }

    private fun loadObjects() {
        Napier.e("ListViewModel - Iniciando carregamento de objetos")
        viewModelScope.launch {
            userCase.execute(Unit).handleResult(
                success = { flow ->
                    Napier.e("ListViewModel - Flow recebido, iniciando coleta")
                    launch {
                        flow.collect { museumObjects ->
                            Napier.e("ListViewModel - Novos objetos recebidos: ${museumObjects.size}")
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