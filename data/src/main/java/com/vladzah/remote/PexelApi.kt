package com.vladzah.remote

import com.vladzah.data.BuildConfig
import com.vladzah.remote.dto.QueryCollectionsDto
import com.vladzah.remote.dto.QueryPhotosDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface PexelApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): QueryPhotosDto

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
    ): QueryCollectionsDto

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
    }
}
