package com.noby.home.data.model

data class ForecastResponseDTO(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
