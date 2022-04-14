package com.marko590.tabtestfinal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class, Day::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun foodDao(): FoodDao
    abstract fun dayDao(): DayDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context) : AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "appDatabase").build()

                INSTANCE = instance
                return instance
            }
        }
    }
}