package com.zs.wallet

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zs.wallet.base.db.AppDatabase
import com.zs.wallet.base.db.UserDao
import com.zs.wallet.user.api.model.UserVo
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 *
 * desc:
 * date: 2019-12-18 16:04
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.UserDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertUserAndQueryTest() {
        val user = UserVo(1, "jack", 1)
        userDao.insertAll(user)

        val result = userDao.loadAllByIds(intArrayOf(1))
        assertThat(result[0], equalTo(user))
    }
}