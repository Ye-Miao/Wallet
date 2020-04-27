package com.zs.lib.base.utils.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 *
 * desc: Fragment 扩展
 * date: 2019-12-20 10:48
 */

inline fun <reified VM : ViewModel> Fragment.viewModel(): VM {
    return ViewModelProviders.of(this).get(VM::class.java)
}

inline fun <reified VM : ViewModel> Fragment.viewModel(activity: AppCompatActivity): VM {
    return ViewModelProviders.of(activity).get(VM::class.java)
}