package com.zs.wallet.main.repo

import com.zs.lib.base.utils.UtilsCode
import com.zs.wallet.base.db.AppDatabase
import com.zs.wallet.base.db.UserDao
import com.zs.wallet.user.api.model.UserVo

/**
 *
 * desc:  main 模块本地数据操作逻辑
 * date: 2019-12-18 16:17
 */
class MainLocalDataSource {
    private val db = AppDatabase.getInstance(UtilsCode.getAppContext())
    private val userDao: UserDao by lazy { db.UserDao() }

    suspend fun loadDbUser(): List<UserVo> {
//        userDao.insertAll(UserVo(1, "jack", 1))
        return userDao.loadAllByIds(intArrayOf(1))
    }
}