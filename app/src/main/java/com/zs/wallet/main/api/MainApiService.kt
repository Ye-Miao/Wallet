package com.zs.wallet.main.api


import com.zs.wallet.base.HttpRequestBody
import com.zs.wallet.main.api.model.MainVo
import com.zs.wallet.main.api.model.PublicInfoVo
import com.zs.wallet.base.ResultVo
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *
 * desc: main 模块相关请求接口
 * date: 2019-12-16 18:01
 */
interface MainApiService {

    // 演示接口
    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun contributors(@Path("owner") owner: String, @Path("repo") repo: String)
            : Response<List<MainVo>>

    /**
     * 获取公共接口的信息
     */
    @POST("/exchange-app-api/common/public_info_v4")
    suspend fun getPublicInfo(@Body requestBody: HttpRequestBody): Response<ResultVo<PublicInfoVo>>

    /**
     * 方式2
     */
    @POST("/exchange-app-api/common/public_info_v4")
    suspend fun getPublicInfo2(@Body requestBody: HttpRequestBody): ResultVo<PublicInfoVo>
}