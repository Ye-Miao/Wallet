package com.zs.lib.base.utils.ext

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 *
 * desc: Context 扩展
 * date: 2020-01-03 16:09
 */

/**
 * 创建一个Fragment
 * @param args 参数
 */
inline fun <reified F : Fragment> Context.newFragment(vararg args: Pair<String, String>): F {
    val bundle = Bundle()
    args.let {
        for (arg in args) {
            bundle.putString(arg.first, arg.second)
        }
    }
    return Fragment.instantiate(this, F::class.java.name, bundle) as F
}

/**
 * 创建一个Fragment
 * @param bundle 参数
 */
inline fun <reified F : Fragment> Context.newFragment(bundle: Bundle? = null): F {
    return Fragment.instantiate(this, F::class.java.name, bundle) as F
}