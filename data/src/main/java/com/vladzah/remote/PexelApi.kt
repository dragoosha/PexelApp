package com.vladzah.remote

import com.vladzah.data.BuildConfig
import com.vladzah.remote.Constants.CURATED_NUMBER
import com.vladzah.remote.Constants.FEATURED_NUMBER
import com.vladzah.remote.dto.PhotoDto
import com.vladzah.remote.dto.QueryCollectionsDto
import com.vladzah.remote.dto.QueryPhotosDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface PexelApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): QueryPhotosDto

    @GET("curated")
    suspend fun getPopularPhotos(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int = CURATED_NUMBER
    ): QueryPhotosDto

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("per_page") pageSize: Int = FEATURED_NUMBER
    ): QueryCollectionsDto

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Path("id") id: Int
    ): PhotoDto

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
    }
}
