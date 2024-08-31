package com.vladzah.di

import com.vladzah.usecases.GetPhotoByIdUsecase
import com.vladzah.usecases.GetPhotoByIdUsecaseImpl
import com.vladzah.usecases.GetPhotosUsecase
import com.vladzah.usecases.GetPhotosUsecaseImpl
import com.vladzah.usecases.GetTitleUsecase
import com.vladzah.usecases.GetTitleUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetPhotosUsecase(getPhotosUsecaseImpl: GetPhotosUsecaseImpl): GetPhotosUsecase
    @Binds
    abstract fun bindGetTitlesUsecase(getTitleUsecaseImpl: GetTitleUsecaseImpl): GetTitleUsecase

    @Binds
    abstract fun bindGetPhotoByIdUsecase(getPhotoByIdUsecaseImpl: GetPhotoByIdUsecaseImpl): GetPhotoByIdUsecase
}