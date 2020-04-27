package com.zs.lib.base.net

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 *
 * desc:  网络引擎,发起网络请求总入口
 * date: 2019-12-16 15:31
 */
class NetEngine private constructor() {

    companion object {
        val instance: NetEngine by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { NetEngine() }
    }

    private var mContext: Context? = null
    private var mIsDebug: Boolean = false
    private var mOkHttpClient: OkHttpClient? = null
    private var mCache: Cache? = null
    private var interceptors = arrayListOf<Interceptor>()
    private var connectTimeout = 60L
    private var writeTimeout = 60L
    private var readTimeout = 60L
    private var sslSocketFactory: SslSocketFactory? = null

    private var retrofitMap = ConcurrentHashMap<String, Retrofit>()

    fun init(context: Context, isDebug: Boolean = false, cache: Cache? = null): NetEngine {
        this.mContext = context
        this.mCache = cache
        this.mIsDebug = isDebug
        return this
    }

    fun addInterceptor(interceptor: Interceptor): NetEngine {
        interceptors.add(interceptor)
        return this
    }

    fun setTimeOut(
        connectTimeout: Long = 60,
        writeTimeout: Long = 60,
        readTimeout: Long = 60
    ): NetEngine {
        this.connectTimeout = connectTimeout
        this.writeTimeout = writeTimeout
        this.readTimeout = readTimeout
        return this
    }

    fun setSslSocketFactory(
        bksFile: InputStream?,
        password: String?,
        vararg certificates: InputStream
    ) {
        sslSocketFactory = SslSocketFactory(bksFile, password, *certificates)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        if (mContext == null) throw RuntimeException("Must call init(context, cache) first !")

        if (mOkHttpClient == null) {
            mOkHttpClient = provideNewOkHttpClient()
        }
        return mOkHttpClient!!
    }

    private fun provideNewOkHttpClient(): OkHttpClient {
        if (mContext == null) throw RuntimeException("Must call init(context, cache) first !")

        val builder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .cache(mCache)
        if (mIsDebug) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(
                sslSocketFactory!!.getSSLSocketFactory(),
                sslSocketFactory!!.getTrustManager()
            )
        }

        interceptors.forEach {
            builder.addInterceptor(it)
        }

        mOkHttpClient = builder.build()
        return mOkHttpClient!!
    }

    fun provideRetrofit(baseUrl: String): Retrofit {
        if (retrofitMap.containsKey(baseUrl)) {
            return retrofitMap[baseUrl]!!
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitMap[baseUrl] = retrofit
        return retrofit
    }

    fun provideNewRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}