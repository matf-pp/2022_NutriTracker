package com.marko590.tabtestfinal.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert
    suspend fun addFood(food: Food)

    @Query("SELECT * FROM food")
    fun readAllData(): LiveData<List<Food>>
}