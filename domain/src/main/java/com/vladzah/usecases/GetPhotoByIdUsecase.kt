package com.vladzah.usecases

import com.vladzah.interfaces.PhotoRepository
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPhotoByIdUsecase {
    suspend fun execute(id: Int) : Flow<PhotoModel>
}

class GetPhotoByIdUsecaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotoByIdUsecase {
    override suspend fun execute(id: Int): Flow<PhotoModel> {
        return photoRepository.getPhotoById(id)
    }

}