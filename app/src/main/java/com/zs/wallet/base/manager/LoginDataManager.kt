package com.zs.wallet.base.manager

/**
 *
 * desc: 本地用户登录信息管理
 * date: 2019-12-17 15:24
 */
class LoginDataManager {

    var mToken: String? = ""
    var mLktToken: String? = ""
    var mIsLogin: Boolean = false

    fun setToken(token: String) {
        mToken = token
        mIsLogin = true
    }

    fun setLktToken(lktToken: String) {
        mLktToken = lktToken
    }
}