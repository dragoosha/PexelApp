package com.vladzah.di

import com.vladzah.usecases.DownloadImageUsecase
import com.vladzah.usecases.DownloadImageUsecaseImpl
import com.vladzah.usecases.ToggleBookmarkStatusUseCase
import com.vladzah.usecases.ToggleBookmarkStatusUseCaseImpl
import com.vladzah.usecases.getUsecases.GetBookmarksUsecase
import com.vladzah.usecases.getUsecases.GetBookmarksUsecaseImpl
import com.vladzah.usecases.getUsecases.GetPhotoByIdFromApiUsecase
import com.vladzah.usecases.getUsecases.GetPhotoByIdFromApiUsecaseImpl
import com.vladzah.usecases.getUsecases.GetPhotoByIdFromDbUsecase
import com.vladzah.usecases.getUsecases.GetPhotoByIdFromDbUsecaseImpl
import com.vladzah.usecases.getUsecases.GetPhotosUsecase
import com.vladzah.usecases.getUsecases.GetPhotosUsecaseImpl
import com.vladzah.usecases.getUsecases.GetTitleUsecase
import com.vladzah.usecases.getUsecases.GetTitleUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetPhotosUsecase(
        getPhotosUsecaseImpl: GetPhotosUsecaseImpl
    ): GetPhotosUsecase

    @Binds
    abstract fun bindGetBookmarkUsecase(
        getBookmarksUsecaseImpl: GetBookmarksUsecaseImpl
    ): GetBookmarksUsecase
    @Binds
    abstract fun bindGetTitlesUsecase(
        getTitleUsecaseImpl: GetTitleUsecaseImpl
    ): GetTitleUsecase

    @Binds
    abstract fun bindGetPhotoByIdFromDbUsecase(
        getPhotoByIdFromDbUsecaseImpl: GetPhotoByIdFromDbUsecaseImpl
    ): GetPhotoByIdFromDbUsecase

    @Binds
    abstract fun bindGetPhotoByIdUsecase(
        getPhotoByIdFromApiUsecaseImpl: GetPhotoByIdFromApiUsecaseImpl
    ): GetPhotoByIdFromApiUsecase

    @Binds
    abstract fun bindDownloadImageUsecase(
        downloadImageUsecaseImpl: DownloadImageUsecaseImpl
    ):DownloadImageUsecase
    @Binds
    abstract fun bindToggleBookmarkStatusUsecase(
        toggleBookmarkStatusUseCaseImpl: ToggleBookmarkStatusUseCaseImpl
    ):ToggleBookmarkStatusUseCase
}