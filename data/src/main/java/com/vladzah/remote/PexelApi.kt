package com.vladzah.remote

import com.vladzah.remote.dto.QueryCollectionsDto
import com.vladzah.remote.dto.QueryPhotosDto
import retrofit2.http.GET
import retrofit2.http.Query


interface PexelApi {

    @GET("search")
    suspend fun getPhotosByQuery(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): QueryPhotosDto

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
    ): QueryCollectionsDto
}
