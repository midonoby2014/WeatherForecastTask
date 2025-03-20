package com.noby.home.data.datasource.remote

import com.noby.core.utils.API_KEY
import com.noby.core.utils.DEFAULT_WEATHER_DESTINATION
import com.noby.core.utils.NUMBER_OF_DAYS
import com.noby.home.data.model.ForecastResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiServices {

    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key: String = API_KEY,
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): ForecastResponseDTO

}