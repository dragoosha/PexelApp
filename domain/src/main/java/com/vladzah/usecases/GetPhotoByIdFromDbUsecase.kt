package com.vladzah.usecases

import com.vladzah.interfaces.PhotoRepository
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPhotoByIdFromDbUsecase {
    suspend fun execute(id: Int) : Flow<PhotoModel>
}

class GetPhotoByIdFromDbUsecaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotoByIdFromDbUsecase {
    override suspend fun execute(id: Int): Flow<PhotoModel> {
        return photoRepository.getPhotoByIdFromDb(id)
    }

}