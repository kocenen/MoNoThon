package com.example.monothon.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sun_history_table")
data class SunHistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

)