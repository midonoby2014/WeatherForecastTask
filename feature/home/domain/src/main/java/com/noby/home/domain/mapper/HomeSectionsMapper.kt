package com.noby.home.domain.mapper

import com.noby.home.data.model.Condition
import com.noby.home.data.model.ForecastResponseDTO
import com.noby.home.data.model.Forecastday
import com.noby.home.data.model.Hour
import com.noby.home.domain.model.ConditionDO
import com.noby.home.domain.model.ForecastDO
import com.noby.home.domain.model.HourDo
import com.noby.home.domain.model.Weather


fun ForecastResponseDTO.toWeather(): Weather = Weather(
    temperature = current.temp_c.toInt(),
    date = forecast.forecastday[0].date,
    wind = current.wind_kph.toInt(),
    humidity = current.humidity,
    feelsLike = current.feelslike_c.toInt(),
    condition = current.condition.toCondition(),
    uv = current.uv.toInt(),
    name = location.name,
    forecasts = forecast.forecastday.map { networkForecastday ->
        networkForecastday.toWeatherForecast()
    }
)

fun Forecastday.toWeatherForecast(): ForecastDO = ForecastDO(
    date = date,
    maxTemp = day.maxtemp_c.toInt().toString(),
    minTemp = day.mintemp_f.toInt().toString(),
    sunrise = astro.sunrise,
    sunset = astro.sunset,
    icon = day.condition.icon,
    hour = hour.map { networkHour ->
        networkHour.toHour()
    }
)

fun Hour.toHour(): HourDo = HourDo(
    time = time,
    icon = condition.icon,
    temperature = temp_c.toInt().toString(),
)



fun Condition.toCondition(): ConditionDO = ConditionDO(
    text =text ,
    icon = icon,
    code = code
)
