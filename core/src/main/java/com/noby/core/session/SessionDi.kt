package com.noby.core.session

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.foundation.datastore.pref.EncryptedPref
import com.foundation.device.DeviceManager
import com.foundation.threading.Dispatcher
import com.foundation.threading.ForIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionDi {

    @Singleton
    @Provides
    internal fun provideEncryptedPref(@ApplicationContext context: Context): EncryptedPref {
        return EncryptedPref(context)
    }

    @Provides
    @Singleton
    fun provideDeviceManager(@ApplicationContext context: Context): DeviceManager {
        return DeviceManager.Factory().create(context = context)
    }

    @Provides
    @Singleton
    fun provideUnauthorizedEvent(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }

}