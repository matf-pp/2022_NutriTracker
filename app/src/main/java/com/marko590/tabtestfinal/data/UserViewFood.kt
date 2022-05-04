package com.marko590.tabtestfinal.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewFood(application: Application): AndroidViewModel(application) {

    val readAllFood: LiveData<List<Food>>
    private val repository: AppRepository
    init{
        val foodDao = AppDatabase.getDatabase(application).foodDao()
        val dayDao = AppDatabase.getDatabase(application).dayDao()
        repository=AppRepository(foodDao,dayDao)
        readAllFood = repository.readAllFood
    }

    fun addFood(food:Food){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFood(food)
        }
    }

    fun addDay(day:Day){
        viewModelScope.launch(Dispatchers.IO){
            repository.addDay(day)
        }
    }

    fun getByName(name: String): LiveData<Food>{
        var data: LiveData<Food>? = null
        viewModelScope.launch(Dispatchers.IO){
            data = repository.getByName(name)
        }
        return data as LiveData<Food>
    }

}