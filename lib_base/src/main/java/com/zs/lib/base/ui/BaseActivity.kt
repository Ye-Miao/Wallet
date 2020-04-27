package com.zs.lib.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zs.lib.base.R
import kotlinx.android.synthetic.main.base_toolbar_activity.*

/**
 *
 * desc: 基类Activity
 * date: 2019-12-16 11:43
 */
abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val BUNDLE_DATA: String = "activity_bundle_data"
        fun <T : BaseActivity> startActivity(
            context: Context,
            clazz: Class<T>,
            args: Bundle? = null
        ) {
            val intent = Intent(context, clazz)
            if (args != null) {
                intent.putExtra(BUNDLE_DATA, args)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var contentView = setLayout()
        if (showToolBar()) {
            if (contentView == 0) {
                contentView = R.layout.base_toolbar_activity
            }
        } else {
            if (contentView == 0) {
                contentView = R.layout.base_activity
            }
        }
        setContentView(contentView)
        iv_back?.setOnClickListener { onBackClick() }
        initViews(savedInstanceState)
        initData(intent.getBundleExtra(BUNDLE_DATA))
    }

    /**设置布局,如果没有指定,则使用默认布局*/
    protected open fun setLayout(): Int = 0

    /**是否显示toolbar ,默认显示,不显示则返回false,目前只有使用默认布局才有效,如果使用了自己布局则该变量无效*/
    protected open fun showToolBar(): Boolean = true

    /**
     * 设置标题 , 只有在{@link #showToolBar()}返回true且{@link #setLayout()}返回0才有效,
     * 如果子类自己需要有自己的toolBar方式,则重写该方法
     * @param title 标题
     */
    protected open fun setTitle(title: String?) {
        if (showToolBar()) {
            tv_center_title?.text = title
        }
    }

    /**返回监听处理方法*/
    protected open fun onBackClick() {
        finish()
    }

    /**
     * 承载的Fragment
     * @param target target fragment
     * @param tag fragment flag
     */
    protected fun setupWithFragment(target: Fragment, tag: String? = null) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_root, target, tag)
            .commitAllowingStateLoss()
    }

    /**
     * 初始化view
     * @param savedInstanceState 对应{@link #onCreate(savedInstanceState: Bundle?)}的savedInstanceState
     */
    protected abstract fun initViews(savedInstanceState: Bundle?)

    /**
     * 初始化数据:比如数据请求等
     * @param args 对应key为{@link BaseActivity#BUNDLE_DATA}的值,
     *              也就是{@link #startActivity(..,args: Bundle?)}仲的bundle
     */
    protected abstract fun initData(args: Bundle?)
}