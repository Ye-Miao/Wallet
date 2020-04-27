package com.zs.lib.imageloader.core

/**
 *
 * desc:  图片结果回调
 * date: 2019-12-18 14:37
 */
interface ImageCallback<in T> {
    fun onLoadSuccess(resource: T?)
    fun onLoadFailed(errorResource: T?)
}