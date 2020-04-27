package com.zs.wallet.splash

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import com.zs.lib.base.ui.BasePermissionActivity
import com.zs.lib.base.utils.ext.startActivity
import com.zs.lib.base.utils.ext.viewModel
import com.zs.lib.imageloader.ImageLoader
import com.zs.lib.imageloader.core.ImageConfig
import com.zs.lib.log.Logger
import com.zs.wallet.R
import com.zs.wallet.main.MainActivity
import kotlinx.android.synthetic.main.splash_activity.*

/**
 *
 * desc: 闪屏页面
 * date: 2019-12-18 10:36
 */
class SplashActivity : BasePermissionActivity() {

    private val splashViewModel by lazy { viewModel<SplashViewModel>() }

    override fun setLayout(): Int = R.layout.splash_activity

    override fun onRequestPermissionSuccess() {
        Logger.e("获取权限成功.....")
        splashViewModel.startCountDown(1)
    }

    override fun onRequestPermissionFail(deniedPermissions: MutableList<String>) {
        Logger.e("获取权限失败.....$deniedPermissions")
    }

    override fun initViews(savedInstanceState: Bundle?) {

    }

    @SuppressLint("SetTextI18n")
    override fun initData(args: Bundle?) {

        ImageLoader.load(
            this, ImageConfig.Builder()
                .imageResource("https://p0.meituan.net/dpmerchantpic/04d47cb5f7cedbec34e270345ca0d98e635305.jpg%40watermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20")
                .imageView(iv_splash)
                .build()
        )

        splashViewModel.splashCountDownLiveData.observe(this, Observer {
            if (it == 0) {
                jumpMain()
                return@Observer
            }
            tv_count_down.text = "${it}S"
        })

        requestPermission(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun jumpMain() {
        startActivity<MainActivity>()
        finish()
    }
}