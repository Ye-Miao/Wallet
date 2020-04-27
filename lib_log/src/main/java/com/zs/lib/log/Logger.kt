package com.zs.lib.log

import android.util.Log

/**
 *
 * desc:  日志类入库,控制debug打印,release关闭
 * date: 2019-12-17 11:52
 */
class Logger {

    companion object {
        private var mIsDebug = false
        private const val TAG = "debug_log"

        fun init(isDebug: Boolean) {
            this.mIsDebug = isDebug
        }

        fun i(msg: String, tag: String? = TAG) {
            if (mIsDebug) {
                Log.i(tag, msg)
            }
        }

        fun d(msg: String, tag: String? = TAG) {
            if (mIsDebug) {
                Log.d(tag, msg)
            }
        }

        fun w(msg: String, tag: String? = TAG) {
            if (mIsDebug) {
                Log.w(tag, msg)
            }
        }

        fun e(msg: String, tag: String? = TAG) {
            if (mIsDebug) {
                Log.e(tag, msg)
            }
        }
    }
}