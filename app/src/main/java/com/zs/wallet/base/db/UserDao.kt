package com.zs.wallet.base.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zs.wallet.user.api.model.UserVo

/**
 *
 * desc:
 * date: 2019-12-18 15:53
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<UserVo>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<UserVo>

    @Insert
    fun insertAll(vararg users: UserVo)

    @Delete
    fun delete(user: UserVo)
}