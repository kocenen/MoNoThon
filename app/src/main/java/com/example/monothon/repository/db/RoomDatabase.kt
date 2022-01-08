package com.example.monothon.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SunHistoryItem::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SunHistoryDao
}
