package com.noby.home.presentation.sections

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.noby.home.presentation.R

import com.noby.home.presentation.WeatherViewModel
import com.noby.home.presentation.components.Animation
import com.noby.home.presentation.components.WeatherErrorState
import com.noby.home.presentation.components.WeatherSuccessState
import com.noby.home.presentation.state.WeatherUiState

@Composable
fun WeatherScreenContent(
    uiState: WeatherUiState,
    viewModel: WeatherViewModel?,
) {
    when {
        uiState.isLoading -> {
            Animation(modifier = Modifier.fillMaxSize(), animation = R.raw.animation_loading)
        }

        uiState.error != null -> {
            WeatherErrorState(uiState = uiState, viewModel = viewModel)
        }

        uiState.weather != null -> {
            WeatherSuccessState( uiState = uiState)
        }
    }
}