package com.vladzah.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

interface PexelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: PexelEntity)

    @Query("SELECT * FROM pexelentity")
    fun getPhotos(): List<PexelEntity>

    @Query("DELETE FROM pexelentity WHERE id = :id")
    fun deletePhoto(id: Long)

    @Query("SELECT isBookmarked FROM pexelentity WHERE id = :id")
    fun isBookmarked(id: Long): Boolean
}