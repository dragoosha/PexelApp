package com.vladzah.pexelapp.models

import com.vladzah.model.PhotoModel

data class PhotoUiModel(
    val id: Int,
    val url: String,
    val width: Int,
    val height: Int,
    val photographer: String,
    val isBookmarked: Boolean
)
internal fun PhotoModel.toUiModel(): PhotoUiModel {
    return PhotoUiModel(
        id = id,
        url = urlOrig,
        width = width,
        height = height,
        photographer = photographer,
        isBookmarked = isBookmarked
    )
}
