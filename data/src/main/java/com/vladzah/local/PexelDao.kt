package com.vladzah.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PexelDao {
    @Upsert
    suspend fun upsertAll(recipes: List<PexelEntity>)

    @Query("SELECT * FROM PexelEntity")
    fun pagingSource(): PagingSource<Int, PexelEntity>

    @Query("SELECT * FROM PexelEntity WHERE isBookmarked = 1")
    fun getBookmarked(): PagingSource<Int, PexelEntity>

    @Query("DELETE FROM PexelEntity WHERE isBookmarked = 0")
    suspend fun clearAll()

    @Query("UPDATE PexelEntity SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun toggleBookmarkStatus(id: Int, isBookmarked: Boolean )

    @Query("SELECT * FROM PexelEntity WHERE id = :id")
    fun getFromDbById(id: Int): Flow<PexelEntity>
}