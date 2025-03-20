package com.noby.home.domain

import com.noby.home.data.repo.HomeRepo
import com.noby.home.domain.usecase.HomeUseCase
import com.noby.home.domain.usecase.HomeUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainDi {

    @Provides
    @ViewModelScoped
    fun provideHomeUseCase(
        homeRepo: HomeRepo
    ): HomeUseCase {
        return HomeUseCaseImp(homeRepo)
    }
}