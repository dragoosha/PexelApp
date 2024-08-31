package com.vladzah.interfaces

import androidx.paging.PagingData
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotos(query: String): Flow<PagingData<PhotoModel>>
    suspend fun getFeatureCollections(): List<String>
    suspend fun getPhotoByIdFromApi(id: Int): Flow<PhotoModel>
    suspend fun getPhotoByIdFromDb(id: Int): Flow<PhotoModel>
}
