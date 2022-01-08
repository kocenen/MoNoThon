package com.example.monothon.repository.db

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.monothon.model.SunType

@Entity(tableName = "sun_history_table")
data class SunHistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val sunBreakType: SunType,
    val isBreak: Boolean,
    val image: Uri
)