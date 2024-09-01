package com.vladzah.mappers

import com.vladzah.model.PhotoModel
import com.vladzah.local.PexelEntity
import com.vladzah.remote.dto.PhotoDto

internal fun PhotoDto.toEntity() : PexelEntity {

    return PexelEntity(
        id = id,
        width = width,
        height = height,
        photographer = photographer,
        urlOrig = urls.original,
        urlComp = urls.compressed,
        isBookmarked = false
    )
}

internal fun PexelEntity.toModel() : PhotoModel {
    return PhotoModel(
        id = id,
        width = width,
        height = height,
        photographer = photographer,
        urlComp = urlComp,
        urlOrig = urlOrig,
        isBookmarked = isBookmarked
    )
}