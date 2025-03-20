package com.noby.home.domain.usecase

import android.content.ContentValues.TAG
import android.util.Log
import com.noby.core.Result
import com.noby.core.models.ERROR_HTTP_FAILED
import com.noby.core.models.ERROR_IO_Exception
import com.noby.core.models.Error
import com.noby.core.models.GENERAL_ERROR
import com.noby.home.data.model.ForecastResponseDTO
import com.noby.home.data.repo.HomeRepo
import com.noby.home.domain.mapper.toWeather
import com.noby.home.domain.model.Weather
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

interface HomeUseCase {
    suspend fun getWeather(city :String): Result<Weather>
}

class HomeUseCaseImp @Inject constructor(
    private val homeRepo: HomeRepo,
) : HomeUseCase {
    override suspend fun getWeather(city :String): Result<Weather> {
        return try {
            val result: ForecastResponseDTO = homeRepo.getWeather(city)
                Result.Success(
                    result.toWeather()
                )
        }
        catch (exception: HttpException) {
            Result.Fail(
                Error(
                    ERROR_HTTP_FAILED,
                    exception.message.orEmpty()
                )
            )
        } catch (exception: IOException) {
            Result.Fail(
                Error(
                    ERROR_IO_Exception,
                    "Please check your network connection and try again!"
                )
            )
        }
        catch (e: Exception){
            Result.Fail(
                Error(
                    GENERAL_ERROR,
                    "GENERAL_ERROR"
                )
            )
        }
    }
}