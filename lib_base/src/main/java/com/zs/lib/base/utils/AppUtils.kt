package com.zs.lib.base.utils

import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils

/**
 *
 * desc:
 * date: 2019-12-17 14:44
 */
class AppUtils private constructor() {

    companion object {

        fun getAppVersionCode(): Long {
            return getAppVersionCode(UtilsCode.getApplication().packageName)
        }

        fun getAppVersionCode(packageName: String): Long {
            if (isSpace(packageName)) return -1
            val pi = UtilsCode.getApplication().packageManager.getPackageInfo(packageName, 0)
            return try {
                when {
                    pi == null -> -1
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> pi.longVersionCode
                    else -> pi.versionCode.toLong()
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                -1
            }
        }


        fun isSpace(str: String): Boolean {
            if (TextUtils.isEmpty(str)) return true
            str.forEach {
                if (!it.isWhitespace()) return false
            }
            return true
        }
    }
}