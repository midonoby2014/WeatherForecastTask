package com.noby.core.network


import androidx.lifecycle.MutableLiveData
import com.foundation.device.DeviceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ReqabaInterceptor @Inject constructor(
    private val deviceManager: DeviceManager,
    private val unauthorizedEvent: MutableLiveData<Boolean>
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("device-id", deviceManager.deviceId())
            .build()
        val response = chain.proceed(newRequest)
        return response
    }

}