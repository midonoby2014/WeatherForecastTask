package com.noby.home.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.noby.home.domain.model.ConditionDO
import com.noby.home.domain.model.ForecastDO
import com.noby.home.domain.model.HourDo
import com.noby.home.domain.model.Weather
import com.noby.home.presentation.sections.WeatherScreenContent
import com.noby.home.presentation.sections.WeatherTopAppBar
import com.noby.home.presentation.state.SearchWidgetState
import com.noby.home.presentation.state.WeatherUiState
import kotlin.random.Random

@Composable
fun WeatherScreen() {

    val  viewModel: WeatherViewModel = hiltViewModel()
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            WeatherTopAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED) },
                onSearchClicked = { viewModel.getWeather(it) },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                WeatherScreenContent(uiState = uiState, viewModel = viewModel)
            }
        },
    )
}


@SuppressLint("DefaultLocale")
@Preview(name = "Light Mode", showBackground = true, showSystemUi = true)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    showBackground = true,
    device = PIXEL_XL
)
@Composable
fun WeatherScreenContentPreview() {
    val hourlyForecast = mutableListOf<HourDo>()
    for (i in 0 until 24) {
        hourlyForecast.add(
            HourDo(
                "yyyy-mm-dd ${String.format("%02d", i)}",
                "",
                "${Random.nextInt(18, 21)}"
            )
        )
    }
    val forecasts = mutableListOf<ForecastDO>()
    for (i in 0..9) {
        forecasts.add(
            ForecastDO(
                "2025-3-${String.format("%02d", i)}",
                "${Random.nextInt(18, 21)}",
                "${Random.nextInt(10, 15)}",
                "07:20 am",
                "06:40 pm",
                "",
                hourlyForecast
            )
        )
    }

        Surface {
            WeatherScreenContent(
                WeatherUiState(
                   weather =  Weather(
                        temperature = 19,
                        date = "Mar 20",
                        wind = 22,
                        humidity = 35,
                        feelsLike = 18,
                        uv = 2,
                        name = "Cairo",
                        forecasts = forecasts,
                       condition = ConditionDO(10, "", "Cloudy")
                    ),
                ), viewModel = null
            )
        }
    }
