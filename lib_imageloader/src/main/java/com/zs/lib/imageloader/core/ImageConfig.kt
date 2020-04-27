package com.zs.lib.imageloader.core

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 *
 * desc:  图片适配参数
 * date: 2019-12-18 10:55
 */
class ImageConfig(builder: Builder) {

    val model: Any? = builder.model
    val imageView: ImageView = builder.imageView
    val height: Int = builder.height
    val width: Int = builder.width
    val skipMemoryCache: Boolean = builder.skipMemoryCache
    @DrawableRes
    val placeholderId: Int = builder.placeholderId
    val errorPlaceholderId: Int = builder.errorPlaceholderId
    var scareType: ImageView.ScaleType = builder.scareType
    var isCircle: Boolean = builder.isCircle
    var isRound: Boolean = builder.isRound
    var roundingRadius: Int = builder.roundingRadius

    //    var bitmapImageCallback: ImageCallback<Bitmap>? = builder.bitmapImageCallback
    var drawableImageCallback: ImageCallback<Drawable>? = builder.drawableImageCallback

    class Builder {
        internal var model: Any? = null
        internal lateinit var imageView: ImageView
        internal var height: Int = 0
        internal var width: Int = 0
        internal var skipMemoryCache: Boolean = false
        @DrawableRes
        internal var placeholderId: Int = -1
        @DrawableRes
        internal var errorPlaceholderId: Int = -1
        internal var scareType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP
        internal var isCircle: Boolean = false
        internal var isRound: Boolean = false
        internal var roundingRadius: Int = 0

        //        internal var bitmapImageCallback: ImageCallback<Bitmap>? = null
        internal var drawableImageCallback: ImageCallback<Drawable>? = null

        /**
         * 设置图片资源
         * @param model 图片资源,可以是网络,本地,或者资源id
         */
        fun imageResource(model: Any?): Builder {
            this.model = model
            return this
        }

        fun imageView(imageView: ImageView): Builder {
            this.imageView = imageView
            return this
        }

        fun height(height: Int): Builder {
            this.height = height
            return this
        }

        fun width(width: Int): Builder {
            this.width = width
            return this
        }

        fun skipMemoryCache(skipMemoryCache: Boolean): Builder {
            this.skipMemoryCache = skipMemoryCache
            return this
        }

        fun placeholder(@DrawableRes placeholderId: Int): Builder {
            this.placeholderId = placeholderId
            return this
        }

        fun error(@DrawableRes errorPlaceholderId: Int): Builder {
            this.errorPlaceholderId = errorPlaceholderId
            return this
        }

        fun scareType(scareType: ImageView.ScaleType): Builder {
            this.scareType = scareType
            return this
        }

        /**
         * 圆形图像
         * @param isCircle 是否是圆形
         */
        fun circle(isCircle: Boolean): Builder {
            this.isCircle = isCircle
            return this
        }

        /**
         * 圆角图像
         * @param isRound 是否是圆角
         * @param roundingRadius 圆角大小
         */
        fun roundedCorner(isRound: Boolean, roundingRadius: Int): Builder {
            this.isRound = isRound
            this.roundingRadius = roundingRadius
            return this
        }

        /*fun bitmapImageCallback(imageCallback: ImageCallback<Bitmap>): Builder {
            this.bitmapImageCallback = imageCallback
            return this
        }*/

        /**
         * 设置回调,使用如下:
         * <p>
         * .drawableImageCallback(object : ImageCallback<Drawable> {
         *  override fun onLoadSuccess(resource: Drawable?) {
         *  iv_splash.setImageDrawable(resource)
         *  }
         *
         *       override fun onLoadFailed(errorResource: Drawable?) {
         *
         *       }
         *      })
         * </p>
         * @param imageCallback
         */
        fun drawableImageCallback(imageCallback: ImageCallback<Drawable>): Builder {
            this.drawableImageCallback = imageCallback
            return this
        }

        fun build(): ImageConfig {
            return ImageConfig(this)
        }
    }
}