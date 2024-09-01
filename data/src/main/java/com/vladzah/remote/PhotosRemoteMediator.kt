package com.vladzah.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vladzah.local.PexelDatabase
import com.vladzah.local.PexelEntity
import com.vladzah.mappers.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator(
    private val pexelDatabase: PexelDatabase,
    private val pexelApi: PexelApi,
    private val query: String
): RemoteMediator<Int, PexelEntity>() {

    private var page = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PexelEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    page = 1
                    page
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        page = 1
                    } else {
                        page += 1
                    }
                    page
                }
            }

            val photos = if (query.isEmpty()) {
                pexelApi.getPopularPhotos(page = loadKey, pageCount = state.config.pageSize)
            } else {
                pexelApi.getPhotosByQuery(query = query, page = loadKey, pageCount = state.config.pageSize)
            }

            pexelDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pexelDatabase.pexelDao.clearAll()
                }

                val photosEntities = photos.photos.map { it.toEntity() }
                pexelDatabase.pexelDao.upsertAll(photosEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = photos.photos.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}