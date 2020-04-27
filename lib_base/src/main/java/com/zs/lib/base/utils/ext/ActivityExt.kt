package com.zs.lib.base.utils.ext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 *
 * desc: AppCompatActivity 扩展
 * date: 2019-12-20 10:45
 */
//fun <T : ViewModel> AppCompatActivity.viewModel(clazz: Class<T>): T {
//    return ViewModelProviders.of(this).get(clazz)
//}

inline fun <reified VM : ViewModel> AppCompatActivity.viewModel(): VM {
    return ViewModelProviders.of(this).get(VM::class.java)
}

/**
 * 启动一个Activity
 */
inline fun <reified A : AppCompatActivity> AppCompatActivity.startActivity() {
    startActivity(Intent(this, A::class.java))
}

