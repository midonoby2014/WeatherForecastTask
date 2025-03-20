package com.noby.core.models

data class ImageResponseDTO(
    val data: List<Data>,
    val message: String,
    val result: Boolean
)
data class Data(
    val fileId: String,
    val localId: String
)