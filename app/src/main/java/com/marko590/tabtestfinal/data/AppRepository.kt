package com.marko590.tabtestfinal.data

import androidx.lifecycle.LiveData

class AppRepository(private val foodDao: FoodDao, private val dayDao: DayDao) {

    val readAllFood: LiveData<List<Food>> = foodDao.readAllData()
    val readAllDays: LiveData<List<Day>> = dayDao.readAllData()

    suspend fun addFood(food: Food){
        foodDao.addFood(food)
    }

    suspend fun addDay(day: Day){
        dayDao.addDay(day)
    }

    suspend fun getByName(name: String): LiveData<Food>{
        return foodDao.getByName(name)
    }


}