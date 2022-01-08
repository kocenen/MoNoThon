package com.example.monothon.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.gson.Gson

@Database(entities = [SunHistoryItem::class], version = 6, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun sunHistoryDao(): SunHistoryDao

    companion object {
        private var instance: RoomDB? = null

        @Synchronized
        fun getInstance(context: Context): RoomDB? {
            if (instance == null) {
                synchronized(RoomDB::class){
                    instance =
                        Room
                            .databaseBuilder(
                                context.applicationContext,
                                RoomDB::class.java,
                            "sun_database"
                            )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return instance
        }
    }
}
