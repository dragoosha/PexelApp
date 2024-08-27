package com.vladzah.repositories

import com.vladzah.interfaces.PhotoModel
import com.vladzah.interfaces.PhotoRepository


import com.vladzah.remote.PexelApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private var pexelApi: PexelApi
): PhotoRepository {
    override fun getPhotos(query: String): Flow<PhotoModel> {

    }

    override suspend fun getFeatureCollections(): List<String> {

    }


}