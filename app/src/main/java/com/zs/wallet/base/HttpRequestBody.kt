package com.zs.wallet.base

import android.text.TextUtils

/**
 *
 * desc: http 请求body 参数,如{"sign":"3fb053c84c83f15db09e1a5379c0d6c9","time":"1576553629139"}
 * date: 2019-12-17 11:38
 */
data class HttpRequestBody(
    var sign: String? = null,
    var time: String? = System.currentTimeMillis().toString(),
    var token: String? = null
) {
    override fun toString(): String {
        return "HttpRequestBody(sign=$sign, time=$time, token=$token)"
    }

    /*class HttpRequestBodyBuilder {
        private val bodyParamMap = HashMap<String, String>()

        fun addParam(key: String, value: String?): HttpRequestBodyBuilder {
            if (TextUtils.isEmpty(value)) return this
            bodyParamMap[key] = value!!
            return this
        }

        fun builder(): HttpRequestBody {

            return HttpRequestBody()
        }


//    data class BodyParam(val key: String, val value: String?)
    }*/
}


