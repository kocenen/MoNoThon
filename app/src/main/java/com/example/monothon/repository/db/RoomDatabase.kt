package com.example.monothon.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SunHistoryItem::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun sunHistoryDao(): SunHistoryDao

    companion object {
        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                synchronized(RoomDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabase::class.java,
                        "sun_database"
                    ).build()
                }
            }
            return instance
        }
    }
}
