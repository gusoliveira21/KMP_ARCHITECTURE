package com.gusoliveira.architecture.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.MuseumObject
import domain.usercase.get.GetObjectByIdUserCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val getObjectByIdUserCas : GetObjectByIdUserCase) : ViewModel() {
    private val _obj = MutableStateFlow<MuseumObject?>(null)
    val obj: StateFlow<MuseumObject?> = _obj.asStateFlow()

    fun getObject(objectId: Int): Flow<MuseumObject?> {
        viewModelScope.launch {
            val result = getObjectByIdUserCas.execute(objectId)
            result.handleResult(
                success = { flow ->
                    launch {
                        flow.collect { it : MuseumObject? ->
                            _obj.value = it
                        }
                    }
                },
                error = { throwable ->
                    if (throwable != null) {
                        Napier.e("ListViewModel - Erro ao carregar objetos: ${throwable.message}")
                    }
                    _obj.value = null
                }
            )
        }
        return obj
    }
}
