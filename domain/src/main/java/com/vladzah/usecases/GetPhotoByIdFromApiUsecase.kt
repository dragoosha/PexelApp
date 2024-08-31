package com.vladzah.usecases

import com.vladzah.interfaces.PhotoRepository
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPhotoByIdFromApiUsecase {
    suspend fun execute(id: Int): Flow<PhotoModel>
}

class GetPhotoByIdFromApiUsecaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotoByIdFromApiUsecase {
    override suspend fun execute(id: Int): Flow<PhotoModel> {
        return photoRepository.getPhotoByIdFromApi(id)
    }

}