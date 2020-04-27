package com.zs.wallet.base.manager

/**
 *
 * desc: 本地数据存储管理
 * date: 2019-12-17 14:20
 */
class LocalDataManager {
    companion object {
        val instance: LocalDataManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { LocalDataManager() }
    }


    private var mLoginDataManager: LoginDataManager? = null

    @Synchronized
    fun getLoginDadaManager(): LoginDataManager {
        if (mLoginDataManager == null) mLoginDataManager = LoginDataManager()
        return mLoginDataManager!!
    }
}