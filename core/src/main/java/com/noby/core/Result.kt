package com.noby.core

import com.noby.core.models.Error
import kotlinx.coroutines.flow.Flow

var EmptyDefaultError =100
sealed class Result<T>(
    val data: T? = null,
    val error: Error = Error(code = EmptyDefaultError, "Something went wrong")
) {
    class Success<T>(data: T?) : Result<T>(data)
    class Loading<T> : Result<T>()
    class Fail<T>(messages: Error) : Result<T>(error = messages)
}
