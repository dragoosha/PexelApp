package com.vladzah.di

import android.content.Context
import androidx.room.Room
import com.vladzah.repositories.PhotosRepositoryImpl
import com.vladzah.interfaces.PhotoRepository
import com.vladzah.local.PexelDao
import com.vladzah.local.PexelDatabase
import com.vladzah.remote.PexelApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindPhotosRepository(photosRepositoryImpl: PhotosRepositoryImpl): PhotoRepository

}