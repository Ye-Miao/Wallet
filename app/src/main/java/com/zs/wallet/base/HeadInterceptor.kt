package com.zs.wallet.base

import com.zs.lib.base.utils.AppUtils
import com.zs.lib.base.utils.NetworkUtils
import com.zs.lib.base.utils.SystemUtils
import com.zs.wallet.base.manager.LocalDataManager
import com.zs.wallet.base.utils.LanguageUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * desc:  网络请求公参拦截器
 * date: 2019-12-16 17:28
 */
class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val language = when {
            LanguageUtils.isZh() -> "zh_CN"
            LanguageUtils.isMn() -> "mn_MN"
            LanguageUtils.isRussia() -> "ru_RU"
            LanguageUtils.isKorea() -> "ko_KR"
            LanguageUtils.isJapanese() -> "ja_JP"
            LanguageUtils.isTW() -> "el_GR"
            LanguageUtils.isVietNam() -> "vi_VN"
            else -> "en_US"
        }

        val requestBuild = chain.request()
            .newBuilder()
            .header("Content-Type", "application/json;charset=utf-8")
            .header("SysSDK-CU", SystemUtils.getSystemSdk().toString())
            .header("SysVersion-CU", SystemUtils.getSystemVersion())
            .header("Mobile-Model-CU", SystemUtils.getSystemModel())
            .header("UUID-CU:APP", "xxxxxxxxxxxxxxxx")
            .header("Channel-CU", "")
            .header("Platform-CU", "android")
            .header("exchange-client", "app")
            .header("exchange-language", language)
            .header("Build-CU", AppUtils.getAppVersionCode().toString())
            .header("Network-CU", NetworkUtils.getNetType())

        // 是否登录
        val loginDataManager = LocalDataManager.instance.getLoginDadaManager()
        if (loginDataManager.mIsLogin) {
            requestBuild.header("exchange-token", loginDataManager.mToken ?: "")
                .header("exchange-client", "app")
                .header("lktToken", loginDataManager.mLktToken ?: "")
        }

        return chain.proceed(requestBuild.build())
    }
}