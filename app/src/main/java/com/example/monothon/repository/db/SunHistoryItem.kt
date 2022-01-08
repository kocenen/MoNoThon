package com.example.monothon.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.monothon.model.SunType
import java.io.File

@Entity(tableName = "sun_history_table")
data class SunHistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val sunBreakType: SunType,
    val isBreak: Boolean,
    val date: String,
    val image: File
)