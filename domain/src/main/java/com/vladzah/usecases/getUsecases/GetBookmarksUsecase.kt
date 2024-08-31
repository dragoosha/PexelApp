package com.vladzah.usecases.getUsecases

import androidx.paging.PagingData
import com.vladzah.interfaces.PhotoRepository
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetBookmarksUsecase {
    suspend fun execute(): Flow<PagingData<PhotoModel>>
}

class GetBookmarksUsecaseImpl @Inject constructor(
    private val repository: PhotoRepository
): GetBookmarksUsecase {
    override suspend fun execute(): Flow<PagingData<PhotoModel>> {
        return repository.getBookmarks()
    }

}