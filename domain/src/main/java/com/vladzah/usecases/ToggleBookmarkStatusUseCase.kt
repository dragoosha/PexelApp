package com.vladzah.usecases

import com.vladzah.interfaces.PhotoRepository
import javax.inject.Inject

interface ToggleBookmarkStatusUseCase {
    suspend fun execute(id: Int, isBookmarked: Boolean)
}

class ToggleBookmarkStatusUseCaseImpl @Inject constructor(
    private val repository: PhotoRepository
): ToggleBookmarkStatusUseCase {
    override suspend fun execute(id: Int, isBookmarked: Boolean) {
        repository.toggleBookmarkStatus(id, isBookmarked)
    }
}
