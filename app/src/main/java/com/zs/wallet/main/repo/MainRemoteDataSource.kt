package com.zs.wallet.main.repo

import android.text.TextUtils
import com.zs.lib.base.net.NetEngine
import com.zs.wallet.BuildConfig
import com.zs.wallet.base.HttpRequestBody
import com.zs.wallet.base.ResultVo
import com.zs.wallet.base.utils.EncryptUtils
import com.zs.wallet.main.api.MainApiService
import com.zs.wallet.main.api.model.MainVo
import com.zs.wallet.main.api.model.PublicInfoVo
import retrofit2.Response

/**
 *
 * desc:  Main 模块网络数据请求逻辑
 * date: 2019-12-17 10:13
 */
class MainRemoteDataSource {
    private val mainApiService = lazy {
        NetEngine.instance.provideRetrofit(BuildConfig.BASE_URL).create(MainApiService::class.java)
    }.value

    suspend fun contributors(): Response<List<MainVo>> {
        return mainApiService.contributors("square", "retrofit")
    }

    suspend fun getPublicInfo(token: String? = "sssss"): Response<ResultVo<PublicInfoVo>> {
        val map = HashMap<String, String>()
        if (!TextUtils.isEmpty(token)) {
            map["token"] = token!!
        }

        val httpRequestBody = HttpRequestBody()
        httpRequestBody.token = token
        httpRequestBody.sign = EncryptUtils.encryptParams2Str(map)

        return mainApiService.getPublicInfo(httpRequestBody)
    }

    suspend fun getPublicInfo2(token: String? = "sssss"): ResultVo<PublicInfoVo> {
        val map = HashMap<String, String>()
        if (!TextUtils.isEmpty(token)) {
            map["token"] = token!!
        }

        val httpRequestBody = HttpRequestBody()
        httpRequestBody.token = token
        httpRequestBody.sign = EncryptUtils.encryptParams2Str(map)

        return mainApiService.getPublicInfo2(httpRequestBody)
    }


    /*private fun toRequestBody(params: Map<String, String>): RequestBody {
        return JSONObject(params).toString()
            .toRequestBody("application/json;charset=utf-8".toMediaTypeOrNull())
//        return RequestBody.create(
//            MediaType.parse("application/json;charset=utf-8"),
//            JSONObject(params).toString()
//        )
    }*/
}