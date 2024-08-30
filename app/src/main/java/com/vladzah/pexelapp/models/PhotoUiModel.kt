package com.vladzah.pexelapp.models

import com.vladzah.model.PhotoModel

data class PhotoUiModel (
    val url: String,
    val width: Int,
    val height: Int,
    val photographer: String
)

internal fun PhotoModel.toUiModel(): PhotoUiModel {
    return PhotoUiModel(
        url = urlOrig,
        width = width,
        height = height,
        photographer = photographer
    )
}