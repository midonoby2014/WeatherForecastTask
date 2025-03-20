package com.noby.core.models

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UploadImageRequest (
    val attachments: MultipartBody.Part? ,
    val LocalId: RequestBody ,
    val fileType: RequestBody ,
)