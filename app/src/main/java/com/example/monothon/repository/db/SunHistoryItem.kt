package com.example.monothon.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.monothon.model.SunType

@Entity(tableName = "sun_history_table")
data class SunHistoryItem(
    val sunBreakType: SunType,
    val isBreak: Boolean,
    val date: String,
    val image: String?
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}