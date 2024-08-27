package com.vladzah.interfaces

import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(query: String): Flow<PhotoModel>
    suspend fun getFeatureCollections(): List<String>
}

data class PhotoModel(
    val id: Long
){}