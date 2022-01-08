package com.example.monothon.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface SunHistoryDao {

    @Insert
    fun addHistory(sunHistoryItem: SunHistoryItem)

    @Query("SELECT * FROM sun_history_table")
    fun getSunHistory(): List<SunHistoryItem>
}