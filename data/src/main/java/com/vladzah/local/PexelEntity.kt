package com.vladzah.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PexelEntity(
    @PrimaryKey val id: Long,
    val urlOrig: String,
    val urlComp: String,
    val width: Int,
    val height: Int,
    val authorName: String,
    val isBookmarked: Boolean
)