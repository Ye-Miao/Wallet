package com.zs.lib.imageloader.core

import android.net.Uri
import androidx.annotation.DrawableRes
import java.io.File
import java.net.URL

/**
 *
 * desc: 图片资源的封装:如网络地址,本地图片等
 * date: 2019-12-18 11:28
 */
data class ImageUrl(
    val url: String? = null,
    val file: File? = null,
    val uri: Uri? = null,
    val urL: URL? = null,
    @DrawableRes val resourceId: Int? = null
    ) {
}