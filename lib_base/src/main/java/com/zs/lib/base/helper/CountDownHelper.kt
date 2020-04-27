package com.zs.lib.base.helper

import android.os.Handler
import android.os.Message
import com.zs.lib.log.Logger
import java.lang.ref.WeakReference

/**
 *
 * desc: 倒计时辅助类:使用场景-1.闪屏倒计时 2.发短信倒计时等
 * date: 2019-12-18 17:43
 */
class CountDownHelper(private val countDown: (num: Int) -> Unit) {

    companion object {
        private const val COUNT_DOWN_CODE = 2000
        const val DEFAULT_COUNT_DOWN_NUMBER = 3

        class CountDownHandler(helper: CountDownHelper) : Handler() {
            private val reference = WeakReference<CountDownHelper>(helper)
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val countDownHelper = reference.get() ?: return
                if (msg.what == COUNT_DOWN_CODE) {
                    var countDownNum = msg.arg1
                    Logger.i("countDown=$countDownNum", "Splash")
                    countDownHelper.countDown.invoke(countDownNum)
                    if (countDownNum == 0) {
                        return
                    }
                    countDownNum--
                    sendMessageDelayed(obtainMsg(countDownNum), 1000)
                }
            }
        }

        private fun obtainMsg(countDownNum: Int): Message {
            return Message.obtain().apply {
                what = COUNT_DOWN_CODE
                arg1 = countDownNum
            }
        }
    }

    private val countDownHelper = CountDownHandler(this)

    fun startCountDown(countDownNum: Int = DEFAULT_COUNT_DOWN_NUMBER) {
        countDownHelper.sendMessage(obtainMsg(countDownNum))
    }

    fun onRemove() {
        countDownHelper.removeMessages(COUNT_DOWN_CODE)
    }
}