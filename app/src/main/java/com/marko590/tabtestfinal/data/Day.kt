package com.marko590.tabtestfinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class Day(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val calorie: Float,
    val protein: Float,
    val fat: Float,
    val carbs: Float
)


