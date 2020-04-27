package com.zs.wallet.user.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *
 * desc: 用户信息
 * date: 2019-12-18 15:50
 */
@Entity(tableName = "user")
data class UserVo(
    @PrimaryKey val uid: Int,
    val name: String?,
    val gender: Int
) {

}