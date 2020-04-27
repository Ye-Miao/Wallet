package com.zs.lib.base.ui

import android.content.pm.PackageManager
import android.os.Build

/**
 *
 * desc: 权限适配activity,需要使用权限的时候继承该Activity
 * date: 2019-12-18 10:07
 */
abstract class BasePermissionActivity : BaseActivity() {
    companion object {
        private const val REQUEST_PERMISSION_CODE = 10000
    }

    private val needRequestPermissionList = arrayListOf<String>()

    /**
     * 请求权限
     * @param permissions 权限列表,如{@link Manifest.permission.WRITE_EXTERNAL_STORAGE}
     */
    protected fun requestPermission(vararg permissions: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.forEach {
                if (checkSelfPermission(it) == PackageManager.PERMISSION_DENIED) {
                    needRequestPermissionList.add(it)
                }
            }
            if (needRequestPermissionList.size == 0) {
                onRequestPermissionSuccess()
                return
            }
            requestPermissions(
                needRequestPermissionList.toArray(
                    arrayOfNulls(
                        needRequestPermissionList.size
                    )
                ), REQUEST_PERMISSION_CODE
            )
            return
        }
        onRequestPermissionSuccess()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    needRequestPermissionList.remove(permissions[i])
                }
            }
            if (needRequestPermissionList.size == 0) {
                onRequestPermissionSuccess()
                return
            }
            onRequestPermissionFail(needRequestPermissionList)
        }
    }

    /**
     * 请求权限成功回调,只有所有的都成功才会回调
     */
    protected abstract fun onRequestPermissionSuccess()

    /**
     * 请求权限失败回调,只要有一个不成功都会回调该方法
     * @param deniedPermissions 失败的权限列表
     */
    protected abstract fun onRequestPermissionFail(deniedPermissions: MutableList<String>)
}