package com.noby.home.domain.model




data class Weather(
    val temperature: Int,
    val date: String,
    val wind: Int,
    val humidity: Int,
    val feelsLike: Int,
    val condition: ConditionDO,
    val uv: Int,
    val name: String,
    val forecasts: List<ForecastDO>
)
