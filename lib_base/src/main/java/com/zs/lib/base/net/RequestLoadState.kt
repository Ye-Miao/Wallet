package com.zs.lib.base.net

/**
 *
 * desc:  网络请求加载状态
 * date: 2019-12-20 10:24
 */
sealed class RequestLoadState<out T : Any> {
    data class Success<out T : Any>(val data: T) : RequestLoadState<T>()
    data class Error(
        val code: String? = "",
        val msg: String? = "",
        val throwable: Throwable? = null
    ) : RequestLoadState<Nothing>()

    data class Loading(val msg: String = "Loading") : RequestLoadState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
            is Loading -> "Loading[msg=$msg]"
        }
    }
}