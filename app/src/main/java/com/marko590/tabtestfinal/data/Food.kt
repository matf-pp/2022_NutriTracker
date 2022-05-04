package com.marko590.tabtestfinal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "foodName") val name: String,
    val calorie: Float,
    val protein: Float,
    val fat: Float,
    val carbs: Float
)