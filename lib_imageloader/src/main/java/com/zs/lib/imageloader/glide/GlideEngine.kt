package com.zs.lib.imageloader.glide

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.zs.lib.imageloader.core.IEngine
import com.zs.lib.imageloader.core.ImageConfig

/**
 *
 * desc: Glide 加载,要更换其他项目,只需继承重写即可
 * date: 2019-12-18 11:01
 */
class GlideEngine : IEngine {
    override fun load(activity: Activity, config: ImageConfig) {
        load(Glide.with(activity), config)
    }

    override fun load(fragment: Fragment, config: ImageConfig) {
        load(Glide.with(fragment), config)
    }

    override fun load(view: View, config: ImageConfig) {
        load(Glide.with(view), config)
    }

    override fun load(context: Context, config: ImageConfig) {
        load(Glide.with(context), config)
    }

    @SuppressLint("CheckResult")
    private fun load(requestManager: RequestManager, config: ImageConfig) {
        val requestBuilder = requestManager.load(config.model)
            .skipMemoryCache(config.skipMemoryCache)
        if (config.placeholderId > 0) {
            requestBuilder.placeholder(config.placeholderId)
        }
        if (config.errorPlaceholderId > 0) {
            requestBuilder.error(config.errorPlaceholderId)
        }
        if (config.width > 0 && config.height > 0) {
            requestBuilder.override(config.width, config.height)
        }

        when (config.scareType) {
            ImageView.ScaleType.CENTER_CROP -> transform(requestBuilder, CenterCrop(), config)
            ImageView.ScaleType.CENTER_INSIDE -> transform(requestBuilder, CenterInside(), config)
            ImageView.ScaleType.FIT_CENTER -> transform(requestBuilder, FitCenter(), config)
            else -> transform(requestBuilder, CenterCrop(), config)
        }
        into(requestBuilder, config)
    }

    private fun into(requestBuilder: RequestBuilder<Drawable>, config: ImageConfig) {
        when {
            config.drawableImageCallback != null -> requestBuilder.into(object :
                ImageViewTarget<Drawable>(config.imageView) {
                override fun setResource(resource: Drawable?) {
                    config.drawableImageCallback?.onLoadSuccess(resource)
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    super.onResourceReady(resource, transition)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    config.drawableImageCallback?.onLoadFailed(errorDrawable)
                }
            })
            /*config.bitmapImageCallback != null -> {

            }*/
            else -> requestBuilder.into(config.imageView)
        }
    }

    @SuppressLint("CheckResult")
    private fun transform(
        requestBuilder: RequestBuilder<Drawable>,
        transform: BitmapTransformation,
        config: ImageConfig
    ) {
        when {
            config.isRound -> requestBuilder.transform(
                transform,
                RoundedCorners(config.roundingRadius)
            )
            config.isCircle -> requestBuilder.transform(transform, CircleCrop())
            else -> requestBuilder.transform(transform)
        }
    }

    private fun checkObjectNull(obj: Any?) {
        if (obj == null) throw RuntimeException("$obj can not null !")
    }
}