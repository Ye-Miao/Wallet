package com.zs.wallet.base.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zs.wallet.user.api.model.UserVo

/**
 *
 * desc: 项目数据库入口
 * date: 2019-12-18 15:46
 */
@Database(entities = [UserVo::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "wallet-db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}