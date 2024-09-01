package com.vladzah.di

import com.vladzah.repositories.PhotosRepositoryImpl
import com.vladzah.interfaces.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindPhotosRepository(photosRepositoryImpl: PhotosRepositoryImpl): PhotoRepository

}