package com.marko590.tabtestfinal.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items")
    fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM todo_items WHERE title LIKE :title")
    fun findByTitle(title: String): TodoEntity

    @Insert
    fun insertAll(vararg todo: TodoEntity)

    @Query("SELECT * todo_items WHERE substr(date,5,7) = :month")
    fun findByMonth(month:String): TodoEntity
    @Delete
    fun delete(todo: TodoEntity)

    @Update
    fun updateTodo(vararg todos: TodoEntity)
}

// SELECT * FROM todo_items WHERE DATE '%M'


//2022-02-02