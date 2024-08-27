package com.vladzah.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PexelEntity::class],
    version = 1
)
abstract class PexelDatabase : RoomDatabase() {
    abstract val pexelDao: PexelDao
}
