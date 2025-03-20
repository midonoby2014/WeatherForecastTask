package com.noby.core.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseModel<T>(
    val success: Boolean,
    val errors: List<Error>?,
    var result: T?
)
