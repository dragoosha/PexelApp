package com.vladzah.remote.dto

import com.google.gson.annotations.SerializedName

data class CollectionDto(
    @SerializedName("title")
    val title: String
)
