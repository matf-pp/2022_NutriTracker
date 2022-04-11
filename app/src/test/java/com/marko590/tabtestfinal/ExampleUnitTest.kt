package com.marko590.tabtestfinal

import androidx.room.Room
import androidx.test.InstrumentationRegistry.getContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marko590.tabtestfinal.storage.AppDatabase
import com.marko590.tabtestfinal.storage.TodoDao
import com.marko590.tabtestfinal.storage.TodoEntity
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException
import java.security.AccessController.getContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
