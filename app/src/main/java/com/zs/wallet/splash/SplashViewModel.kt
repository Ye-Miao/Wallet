package com.zs.wallet.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zs.lib.base.helper.CountDownHelper

/**
 *
 * desc: 闪屏业务逻辑
 * date: 2019-12-18 17:26
 */
class SplashViewModel : ViewModel() {

    private val countDownHelper = CountDownHelper(countDown = {
        splashCountDownLiveData.value = it
    })
    val splashCountDownLiveData = MutableLiveData<Int>()


    fun startCountDown(countDownNum: Int = CountDownHelper.DEFAULT_COUNT_DOWN_NUMBER) {
        countDownHelper.startCountDown(countDownNum)
    }

    override fun onCleared() {
        countDownHelper.onRemove()
        super.onCleared()
    }

}