package com.zs.lib.base.utils

import android.os.Build

/**
 *
 * desc:  系统参数工具类
 * date: 2019-12-16 17:30
 */
class SystemUtils private constructor() {

    companion object {

        fun getSystemVersion(): String {
            return Build.VERSION.RELEASE
        }

        fun getSystemSdk(): Int {
            return Build.VERSION.SDK_INT
        }

        fun getSystemModel(): String {
            return Build.MODEL
        }
    }
}