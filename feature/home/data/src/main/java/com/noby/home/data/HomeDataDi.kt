package com.noby.home.data


import com.foundation.network.ServiceFactory
import com.foundation.threading.Dispatcher
import com.foundation.threading.ForIO
import com.noby.home.data.datasource.HomeRemoteDataSource
import com.noby.home.data.datasource.HomeRemoteDataSourceImp
import com.noby.home.data.datasource.remote.HomeApiServices
import com.noby.home.data.repo.HomeRepoImp
import com.noby.home.data.repo.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(ViewModelComponent::class)
object HomeDataDi {


    @Provides
    @ViewModelScoped
    fun provideRemoteDateSource(
        homeAPI: HomeApiServices,
        @ForIO dispatcher: Dispatcher<CoroutineDispatcher>,
    ): HomeRemoteDataSource =
        HomeRemoteDataSourceImp(homeAPI, dispatcher)


    @Provides
    @ViewModelScoped
    fun provideAuthRepo(
        authRemoteDataSource: HomeRemoteDataSource,
        @ForIO dispatcher: Dispatcher<CoroutineDispatcher>,
    ): HomeRepo =
        HomeRepoImp(authRemoteDataSource, dispatcher)

    @Provides
    @ViewModelScoped
    fun provideNetworkClient(
        serviceFactory: ServiceFactory
    ): HomeApiServices =
        serviceFactory.create(HomeApiServices::class.java)

}