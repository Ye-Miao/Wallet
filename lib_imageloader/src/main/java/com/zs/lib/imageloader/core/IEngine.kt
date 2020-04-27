package com.zs.lib.imageloader.core

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

/**
 *
 * desc: 图片加载逻辑接口
 * date: 2019-12-18 10:56
 */
interface IEngine {
    fun load(context: Context, config: ImageConfig)
    fun load(activity: Activity, config: ImageConfig)
    fun load(fragment: Fragment, config: ImageConfig)
    fun load(view: View, config: ImageConfig)

}