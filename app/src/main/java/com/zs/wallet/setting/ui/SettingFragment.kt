package com.zs.wallet.setting.ui


import android.os.Bundle
import com.zs.lib.base.ui.BaseFragment
import com.zs.wallet.R

/**
 *
 * desc:
 * date: 2019-12-16 13:37
 */
class SettingFragment : BaseFragment() {


    override fun setLayout(): Int {
        return R.layout.setting_fragment
    }

    override fun initViews() {
        showContent()
    }

    override fun initData(args: Bundle?) {

    }
}