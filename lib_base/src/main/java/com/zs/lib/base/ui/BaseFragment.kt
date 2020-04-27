package com.zs.lib.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.zs.lib.base.R

/**
 *
 * desc: 基类Fragment
 * date: 2019-12-16 14:20
 */
abstract class BaseFragment : Fragment() {

    private var contentView: View? = null

    // 统一错误页面
    private var vsErrorLayout: ViewStub? = null
    private var vsErrorView: View? = null
    private var tvErrorDesc: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (isShowErrorView()) {
            val fragmentView = inflater.inflate(R.layout.base_fragment, null)
            val rootLayout = fragmentView.findViewById<FrameLayout>(R.id.fl_fragment_root)
            vsErrorLayout = fragmentView.findViewById(R.id.vs_error_layout)
            contentView = LayoutInflater.from(context).inflate(setLayout(), null)
            rootLayout.addView(contentView)
            return fragmentView
        }
        return inflater.inflate(setLayout(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData(arguments)
    }

    /**是否显示公共错误页面,比如无网,请求错误,无数据等,默认不显示*/
    protected open fun isShowErrorView(): Boolean = false


    protected fun showContent() {
        vsErrorView?.visibility = GONE
        contentView?.visibility = VISIBLE
    }

    protected fun showNoNet() {
        showErrorView(R.string.base_error_no_net, 0)
    }

    protected fun showNoData() {
        showErrorView(R.string.base_error_no_data, 0)
    }

    protected fun showError() {
        showErrorView(R.string.base_error_desc, 0)
    }

    protected fun showErrorView(@StringRes errorDescId: Int, @DrawableRes errorResId: Int) {
        if (tvErrorDesc == null) {
            vsErrorView = vsErrorLayout?.inflate()
            tvErrorDesc = vsErrorView?.findViewById(R.id.tv_error_desc)
        }
        tvErrorDesc?.text = getString(errorDescId)
        vsErrorView?.visibility = VISIBLE
        contentView?.visibility = GONE
    }

    /**fragment 布局*/
    protected abstract fun setLayout(): Int

    /**初始化view*/
    protected abstract fun initViews()

    /**初始化数据:比如数据请求等*/
    protected abstract fun initData(args: Bundle?)
}