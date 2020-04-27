package com.zs.lib.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 *
 * desc:
 * date: 2019-12-17 15:15
 */
class NetworkUtils private constructor() {

    companion object {
        fun isMobile(context: Context = UtilsCode.getAppContext()): Boolean {
            val info = getNetworkInfo(context)
            if (info != null) {
                return info.type == ConnectivityManager.TYPE_MOBILE
            }
            return false
        }

        fun isWifi(context: Context = UtilsCode.getAppContext()): Boolean {
            val info = getNetworkInfo(context)
            if (info != null) {
                return info.type == ConnectivityManager.TYPE_WIFI
            }
            return false
        }

        fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        fun getNetType(): String {
            if (isMobile()) return "4G"
            if (isWifi()) return "wifi"
            return ""
        }
    }
}