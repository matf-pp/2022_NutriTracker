package com.marko590.tabtestfinal

import androidx.room.Room
import androidx.test.InstrumentationRegistry.getContext
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marko590.tabtestfinal.storage.AppDatabase
import com.marko590.tabtestfinal.storage.TodoDao
import com.marko590.tabtestfinal.storage.TodoEntity
import org.hamcrest.CoreMatchers
import org.junit.After
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.security.AccessController.getContext

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EnityReadWriteTest {
    private lateinit var todoDao: TodoDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        todoDao = db.TodoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val todo: TodoEntity = TodoEntity(123, "title", "detail")
        todoDao.insertAll(todo)
        val todoItem = todoDao.findByTitle(todo.title)
        assertThat(todoItem, CoreMatchers.equalTo(todo))
    }
}