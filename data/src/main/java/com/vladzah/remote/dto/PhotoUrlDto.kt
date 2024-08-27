package com.vladzah.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoUrlDto(
    @SerializedName("original")
    val original: String,
    @SerializedName("large2x")
    val compressed: String
)
