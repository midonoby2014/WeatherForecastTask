package com.noby.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StateAndEventViewModel<Event>() : ViewModel() {

    private val events = MutableSharedFlow<Event>(replay = 0)

    init {
        viewModelScope.launch {
            events.collect { event ->
                handleEvent(event)
            }
        }
    }
    protected abstract suspend fun handleEvent(event: Event)

    fun onEvent(event: Event) {
        viewModelScope.launch {
            events.emit(event)
        }
    }
}