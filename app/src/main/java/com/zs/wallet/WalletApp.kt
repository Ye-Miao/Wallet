package com.zs.wallet

import android.app.Application
import android.os.StrictMode
import com.github.moduth.blockcanary.BlockCanary
import com.tencent.mmkv.MMKV
import com.zs.lib.base.net.NetEngine
import com.zs.lib.base.utils.UtilsCode
import com.zs.lib.imageloader.ImageLoader
import com.zs.lib.imageloader.glide.GlideEngine
import com.zs.lib.log.Logger
import com.zs.wallet.base.AppBlockCanaryContext
import com.zs.wallet.base.HeadInterceptor
import kotlinx.coroutines.GlobalScope
import leakcanary.AppWatcher
import leakcanary.LeakCanary
import okhttp3.Cache
import java.io.File

class WalletApp : Application() {

    override fun onCreate() {
        super.onCreate()

        UtilsCode.init(this)
        Logger.init(BuildConfig.IS_DEBUG)

        // 初始化mmkv
        MMKV.initialize(this)
        //网络
        val cache = Cache(File(cacheDir, "httpNet"), 1024 * 1024 * 10)
        NetEngine.instance
            .init(applicationContext, BuildConfig.DEBUG, cache)
            .addInterceptor(HeadInterceptor())
            .setSslSocketFactory(null, null, resources.assets.open("cert.cer"))

        // 初始化图片加载
        ImageLoader.init(GlideEngine())

        if (BuildConfig.IS_DEBUG) {
            enabledStrictMode()
            // 主进程
            BlockCanary.install(this, AppBlockCanaryContext()).start()
        }
    }

    private fun enabledStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
//                .detectAll()
//                .detectDiskReads()
//                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .penaltyDialog()
                .penaltyDeath()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                //检测资源是否正确关闭
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        );
    }
}