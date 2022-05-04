package com.marko590.tabtestfinal.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DayDao {

    @Insert
    suspend fun addDay(day: Day)

    @Query("SELECT * FROM days")
    fun readAllData(): LiveData<List<Day>>

}