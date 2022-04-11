package com.marko590.tabtestfinal.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "todo_items")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name= "calories")var calories: Int,
    @ColumnInfo(name = "steps") var steps: Int
)