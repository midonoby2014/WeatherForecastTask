package com.noby.home.domain.model

data class ForecastDO(
    val date: String,
    val maxTemp: String,
    val minTemp: String,
    val sunrise: String,
    val sunset: String,
    val icon: String,
    val hour: List<HourDo>,
)
