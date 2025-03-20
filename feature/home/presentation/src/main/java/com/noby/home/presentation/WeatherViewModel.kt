package com.noby.home.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noby.core.utils.DEFAULT_WEATHER_DESTINATION
import com.noby.home.domain.usecase.HomeUseCase
import com.noby.home.presentation.state.SearchWidgetState
import com.noby.home.presentation.state.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getInitialHomeUseCase: HomeUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()


    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    private fun updateUiState(update: WeatherUiState.() -> WeatherUiState) {
        _uiState.update { _uiState.value.update() }
    }

    init {
        getWeather()
    }

    fun getWeather(city: String = DEFAULT_WEATHER_DESTINATION) {
        viewModelScope.launch {

            updateUiState { copy(isLoading = true) }
            val result = getInitialHomeUseCase.getWeather(city)
            result.data?.let {
                updateUiState {
                    copy(
                        weather = it,
                        isLoading = false,
                        error = null
                    )
                }

            } ?: updateUiState {
                result.error.msg.let {
                    copy(
                        error = it ,
                        isLoading = false
                    )
                }
            }
        }
    }

}