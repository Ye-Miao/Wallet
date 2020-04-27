package com.zs.lib.base.utils

import android.app.Application
import android.content.Context

/**
 *
 * desc: 工具类入口,在application的onCreate仲初始化
 * date: 2019-12-17 14:49
 */
class UtilsCode {
    companion object {
        private lateinit var application: Application

        fun init(application: Application) {
            this.application = application
        }

        fun getApplication(): Application {
            return application
        }

        fun getAppContext(): Context {
            return application.applicationContext
        }
    }
}