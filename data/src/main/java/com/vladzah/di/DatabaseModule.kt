package com.vladzah.di

import android.content.Context
import androidx.room.Room
import com.vladzah.local.PexelDao
import com.vladzah.local.PexelDatabase
import com.vladzah.remote.PexelApi
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
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PexelDatabase {
        return Room.databaseBuilder(
            context,
            PexelDatabase::class.java,
            "pexel_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePexelDao(pexelDatabase: PexelDatabase): PexelDao = pexelDatabase.pexelDao

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(PexelApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(): PexelApi {
        return Retrofit.Builder()
            .baseUrl(PexelApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}