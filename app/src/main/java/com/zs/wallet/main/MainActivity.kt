package com.zs.wallet.main

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zs.lib.base.net.NetEngine
import com.zs.lib.base.net.RequestLoadState
import com.zs.lib.base.ui.BaseActivity
import com.zs.lib.base.utils.ext.viewModel
import com.zs.lib.log.Logger
import com.zs.wallet.BuildConfig
import com.zs.wallet.R
import com.zs.wallet.main.api.MainApiService
import com.zs.wallet.main.vm.MainViewModel
import com.zs.wallet.setting.ui.AboutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mainViewModel by lazy { viewModel<MainViewModel>() }

    override fun setLayout(): Int = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
        sample_text.setOnClickListener {
            startActivity(this, AboutActivity::class.java)
        }
    }

    override fun initData(args: Bundle?) {
        // Example of a call to a native method
//        sample_text.text = stringFromJNI()

//        mainViewModel.github()

        mainViewModel.gitDataLiveData.observe(this, Observer {
            sample_text.text = it.toString()
        })

//        mainViewModel.getPublicInfo()
        mainViewModel.cpmplex()

        mainViewModel.loadDbUsers()

        mainViewModel.loadStateLiveData.observe(this, Observer {
            when (it) {
                is RequestLoadState.Success -> {
                    Logger.e("请求成功...${it.data}")
                }
                is RequestLoadState.Error -> {
                    Logger.e("请求错误...${it.throwable}")
                }
                is RequestLoadState.Loading -> {
                    Logger.e("开始请求...${it.msg}")
                }
            }
        })
    }

    /*external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }*/
}
