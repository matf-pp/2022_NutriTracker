package com.marko590.tabtestfinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class Day(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val calorie: Int,
    val steps: Int
)


