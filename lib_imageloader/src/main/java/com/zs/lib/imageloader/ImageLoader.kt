package com.zs.lib.imageloader

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.zs.lib.imageloader.core.IEngine
import com.zs.lib.imageloader.core.ImageConfig
import java.lang.RuntimeException

/**
 *
 * desc:  图片加载入口,进一步封装,方便后面扩展其他
 * date: 2019-12-18 10:51
 */
class ImageLoader private constructor() {

    companion object {
        private var mIEngine: IEngine? = null

        fun init(iEngine: IEngine) {
            mIEngine = iEngine
        }

        fun load(context: Context, config: ImageConfig) {
            checkEngineNull()
            mIEngine?.load(context, config)
        }


        fun load(activity: Activity, config: ImageConfig) {
            checkEngineNull()
            mIEngine?.load(activity, config)
        }

        fun load(fragment: Fragment, config: ImageConfig) {
            checkEngineNull()
            mIEngine?.load(fragment, config)
        }

        fun load(view: View, config: ImageConfig) {
            checkEngineNull()
            mIEngine?.load(view, config)
        }

        private fun checkEngineNull() {
            if (mIEngine == null) throw RuntimeException("must call init() in application first !")
        }

    }

}