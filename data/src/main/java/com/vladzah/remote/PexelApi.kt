package com.vladzah.remote

import com.vladzah.remote.dto.QueryPhotosDto
import retrofit2.http.GET
import retrofit2.http.Query


interface PexelApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Query("query") query: String
    ): QueryPhotosDto

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
    ): CollectionDto
}
