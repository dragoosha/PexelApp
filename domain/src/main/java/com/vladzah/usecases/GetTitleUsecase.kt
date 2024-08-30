package com.vladzah.usecases

import androidx.paging.PagingData
import com.vladzah.interfaces.PhotoRepository
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTitleUsecase {
    suspend fun execute() : List<String>
}

class GetTitleUsecaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : GetTitleUsecase {
    override suspend fun execute(): List<String> {
        return photoRepository.getFeatureCollections()
    }
}