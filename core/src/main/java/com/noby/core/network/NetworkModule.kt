package com.noby.core.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.foundation.device.DeviceManager
import com.foundation.network.ServiceFactory
import com.foundation.network.retrofit.RetrofitServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

import com.noby.core.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    internal fun provideServiceFactory(
        chuckInterceptor: ChuckerInterceptor,
        deviceManager: DeviceManager,
        unauthorizedEvent: MutableLiveData<Boolean>
    ): ServiceFactory {
        return RetrofitServiceFactory(
            baseUrl = BuildConfig.BASE_URL,
            interceptors = mutableListOf<Interceptor>(
                ReqabaInterceptor(
                    deviceManager = deviceManager,

                    unauthorizedEvent = unauthorizedEvent
                ),
            ).apply {
                if (BuildConfig.DEBUG) {
                    add(chuckInterceptor)
                    add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                }
            },
        )
    }

    @Singleton
    @Provides
    internal fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {

        return ChuckerInterceptor.Builder(context = context)
            .collector(ChuckerCollector(
                    context = context,
                    showNotification = true,))
            .maxContentLength(250_000L)
//                .redactHeaders("authorization", "token") // to hide the token
            .alwaysReadResponseBody(true)
            .build()
    }
}