package com.vladzah.usecases

import android.util.Log
import com.vladzah.interfaces.PhotoRepository
import javax.inject.Inject

interface ToggleBookmarkStatusUseCase {
    suspend fun execute(id: Int, isBookmarked: Boolean)
}

class ToggleBookmarkStatusUseCaseImpl @Inject constructor(
    private val repository: PhotoRepository
): ToggleBookmarkStatusUseCase {
    override suspend fun execute(id: Int, isBookmarked: Boolean) {
        Log.d("toggleBookmarkStatus1", " ${isBookmarked}")
        repository.toggleBookmarkStatus(id, isBookmarked)
    }
}
