package com.vladzah.di

import com.vladzah.usecases.DownloadImageUsecase
import com.vladzah.usecases.DownloadImageUsecaseImpl
import com.vladzah.usecases.GetPhotoByIdFromApiUsecase
import com.vladzah.usecases.GetPhotoByIdFromApiUsecaseImpl
import com.vladzah.usecases.GetPhotoByIdFromDbUsecase
import com.vladzah.usecases.GetPhotoByIdFromDbUsecaseImpl
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
    abstract fun bindGetPhotoByIdFromDbUsecase(getPhotoByIdFromDbUsecaseImpl: GetPhotoByIdFromDbUsecaseImpl): GetPhotoByIdFromDbUsecase

    @Binds
    abstract fun bindGetPhotoByIdUsecase(getPhotoByIdFromApiUsecaseImpl: GetPhotoByIdFromApiUsecaseImpl): GetPhotoByIdFromApiUsecase

    @Binds
    abstract fun bindDownloadImageUsecase(downloadImageUsecaseImpl: DownloadImageUsecaseImpl):DownloadImageUsecase
}