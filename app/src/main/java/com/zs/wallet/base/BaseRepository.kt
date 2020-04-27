package com.zs.wallet.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 *
 * desc:  Repository基类,所以的Repository需要继承这个
 * date: 2019-12-17 17:25
 */
abstract class BaseRepository {

    /**
     * Response cover ResultVo
     * @param response  Response
     * @return ResultVo
     */
    protected fun <T : Any> coverResponse2Result(response: Response<ResultVo<T>>)
            : ResultVo<T> {
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body
        }
        return ResultVo(response.code().toString(), response.message())
    }

    /**
     * 运行在io线程中
     */
    protected suspend fun <T : Any> runInIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            return@withContext block.invoke()
        }
    }
}