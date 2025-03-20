package com.noby.home.data.datasource

import com.foundation.threading.Dispatcher
import com.foundation.threading.ForIO
import com.noby.home.data.datasource.remote.HomeApiServices
import com.noby.home.data.model.ForecastResponseDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HomeRemoteDataSource {
    suspend fun getWeather(city: String): ForecastResponseDTO

}

class HomeRemoteDataSourceImp @Inject constructor(
    private val service: HomeApiServices,
    @ForIO private val dispatcher: Dispatcher<CoroutineDispatcher>
) : HomeRemoteDataSource {
    override suspend fun getWeather(city: String): ForecastResponseDTO =
        withContext(dispatcher()) {
            service.getWeatherForecast(city =  city)
        }
}