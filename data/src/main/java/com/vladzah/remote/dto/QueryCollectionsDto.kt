package com.vladzah.remote.dto

import com.google.gson.annotations.SerializedName

data class QueryCollectionsDto(
    @SerializedName("collections")
    val collection: List<CollectionDto>
)
