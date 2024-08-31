package com.vladzah.usecases.getUsecases

import com.vladzah.interfaces.PhotoRepository
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