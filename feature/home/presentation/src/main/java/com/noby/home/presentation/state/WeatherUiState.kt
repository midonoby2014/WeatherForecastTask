package com.noby.home.presentation.state

import androidx.compose.runtime.Immutable
import com.noby.home.domain.model.Weather

@Immutable
data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
)
