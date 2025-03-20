package com.noby.home.data.repo


import com.foundation.threading.Dispatcher
import com.foundation.threading.ForIO
import com.noby.home.data.datasource.HomeRemoteDataSource
import com.noby.home.data.model.ForecastResponseDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface HomeRepo {

    suspend fun getWeather(city: String): ForecastResponseDTO
}

class HomeRepoImp @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    @ForIO private val dispatcher: Dispatcher<CoroutineDispatcher>,
) : HomeRepo {

    override suspend fun getWeather(city: String): ForecastResponseDTO =
        withContext(dispatcher()) {
            remoteDataSource.getWeather(city)
        }
}