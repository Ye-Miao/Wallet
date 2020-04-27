package com.zs.wallet.main.repo


import com.zs.wallet.base.BaseRepository
import com.zs.wallet.base.ResultVo
import com.zs.wallet.main.api.model.MainVo
import com.zs.wallet.main.api.model.PublicInfoVo
import com.zs.wallet.user.api.model.UserVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import retrofit2.Response

/**
 *
 * desc: Main 逻辑操作
 * date: 2019-12-16 18:26
 */
class MainRepository : BaseRepository() {
    private val mainRemoteDataSource = MainRemoteDataSource()
    private val mainLocalDataSource = MainLocalDataSource()


    suspend fun contributors(): Response<List<MainVo>> {
        return runInIO {
            return@runInIO mainRemoteDataSource.contributors()
        }
    }

    suspend fun getPublicInfo(): ResultVo<PublicInfoVo>? {
        return runInIO {
            return@runInIO coverResponse2Result(mainRemoteDataSource.getPublicInfo())
        }
    }

    suspend fun getPublicInfo2(): ResultVo<PublicInfoVo> {
        return runInIO {
            return@runInIO mainRemoteDataSource.getPublicInfo2()
        }
    }

    suspend fun loadDbUser(): List<UserVo> {
        return withContext(Dispatchers.IO) {
            return@withContext mainLocalDataSource.loadDbUser()
        }
    }
}