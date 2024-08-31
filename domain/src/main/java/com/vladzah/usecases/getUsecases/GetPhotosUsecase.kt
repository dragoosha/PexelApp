package com.vladzah.usecases.getUsecases

import androidx.paging.PagingData
import com.vladzah.interfaces.PhotoRepository
import com.vladzah.model.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetPhotosUsecase {
    suspend fun execute(query:String) : Flow<PagingData<PhotoModel>>

}
class GetPhotosUsecaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
): GetPhotosUsecase {
    override suspend fun execute(query: String): Flow<PagingData<PhotoModel>> {
        return photoRepository.getPhotos(query)
    }
}