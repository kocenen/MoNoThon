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
    suspend fun getSearchHistory(): List<SunHistoryItem>

    @Query("SELECT * FROM sun_history_table")
    fun getSearchHistoryObservable(): Observable<List<SunHistoryItem>>

    @Query("SELECT * FROM sun_history_table")
    fun getSearchHistoryFlow(): Flow<List<SunHistoryItem>>

    @Query("DELETE FROM sun_history_table WHERE name=:name")
    suspend fun deleteSearchHistory(name: String)
}