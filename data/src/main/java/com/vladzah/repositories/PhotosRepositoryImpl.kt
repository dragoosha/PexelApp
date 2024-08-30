package com.vladzah.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.vladzah.model.PhotoModel
import com.vladzah.interfaces.PhotoRepository
import com.vladzah.local.PexelDatabase
import com.vladzah.mappers.toEntity
import com.vladzah.mappers.toModel
import com.vladzah.remote.PexelApi
import com.vladzah.remote.PhotosRemoteMediator
import com.vladzah.remote.dto.QueryPhotosDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PhotosRepositoryImpl @Inject constructor(
    private val pexelApi: PexelApi,
    private val pexelDatabase: PexelDatabase,
): PhotoRepository {
    override suspend fun getPhotos(query: String): Flow<PagingData<PhotoModel>> {
        return withContext(Dispatchers.IO) {
            val pager = Pager(
                config = PagingConfig(pageSize = 20),
                remoteMediator = PhotosRemoteMediator(
                    pexelDatabase = pexelDatabase,
                    pexelApi = pexelApi,
                    query = query
                ),
                pagingSourceFactory = {
                    pexelDatabase.pexelDao.pagingSource()
                }
            )

            pager.flow.map { pagingData ->
                pagingData.map { it.toModel() }
            }
        }
    }


    override suspend fun getFeatureCollections(): List<String> {
        return withContext(Dispatchers.IO) {
            return@withContext pexelApi.getFeaturedCollections().collection
                    .map { collectionDTO ->
                        collectionDTO.title
            }
        }
    }

}