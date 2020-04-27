package com.zs.wallet.base

/**
 *
 * desc:  后台统一返回格式
 * date: 2019-12-17 11:13
 */
data class ResultVo<out T : Any>(
    val code: String?,
    val msg: String?,
    val data: T? = null
) {
    override fun toString(): String {
        return "ResultVo(code=$code, msg=$msg, data=$data)"
    }

    fun isSuccess(): Boolean {
        return "0" == code
    }
}