package com.vladzah.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("src")
    val urls: PhotoUrlDto,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("photographer")
    val photographer: String
)
