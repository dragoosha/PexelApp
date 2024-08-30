package com.vladzah.di

import com.vladzah.usecases.GetPhotosUsecase
import com.vladzah.usecases.GetPhotosUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetPhotosUsecase(getPhotosUsecaseImpl: GetPhotosUsecaseImpl): GetPhotosUsecase
}