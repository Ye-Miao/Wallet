package com.zs.wallet.setting.ui

import android.os.Bundle
import com.zs.lib.base.ui.BaseActivity
import com.zs.lib.base.utils.ext.newFragment

/**
 *
 * desc: 关于界面
 * date: 2019-12-16 13:36
 */
class AboutActivity : BaseActivity() {


    override fun initViews(savedInstanceState: Bundle?) {
        setupWithFragment(newFragment<SettingFragment>())

    }

    override fun initData(args: Bundle?) {
        setTitle("关于")
    }
}