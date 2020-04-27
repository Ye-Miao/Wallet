package com.zs.lib.base.net

import java.io.InputStream
import java.lang.Exception
import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 *
 * desc:  SSL 加密请求
 * date: 2019-12-16 16:16
 */
class SslSocketFactory(
    bksFile: InputStream?,
    password: String?,
    vararg certificates: InputStream
) {
    private var sslSocketFactory: SSLSocketFactory
    private var trustManager: X509TrustManager

    init {
        val trustManagers = prepareTrustManager(*certificates)
        val keyManagers = prepareKeyManager(bksFile, password)
        val sslContext = SSLContext.getInstance("TLS")

        trustManager = if (trustManagers != null) {
            MyTrustManager(chooseTrustManager(trustManagers))
        } else {
            UnSafeTrustManager()
        }
        sslContext.init(keyManagers, arrayOf(trustManager), null)
        sslSocketFactory = sslContext.socketFactory
    }

    fun getSSLSocketFactory(): SSLSocketFactory = sslSocketFactory
    fun getTrustManager(): X509TrustManager = trustManager

    private fun prepareKeyManager(bksFile: InputStream?, password: String?): Array<KeyManager>? {
        if (bksFile == null || password == null) return null
        try {
            val clientKeyStore = KeyStore.getInstance("BKS")
            clientKeyStore.load(bksFile, password.toCharArray())
            val keyManagerFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(clientKeyStore, password.toCharArray())
            return keyManagerFactory.keyManagers
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun prepareTrustManager(vararg certificates: InputStream): Array<TrustManager>? {
        if (certificates.isEmpty()) return null
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null)
            var index = 0
            certificates.forEach {
                index++
                keyStore.setCertificateEntry(
                    index.toString(),
                    certificateFactory.generateCertificate(it)
                )
                it.close()
                val trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                return trustManagerFactory.trustManagers
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun chooseTrustManager(trustManagers: Array<TrustManager>): X509TrustManager? {
        trustManagers.forEach {
            if (it is X509TrustManager) {
                return@forEach
            }
        }
        return null
    }

    inner class UnSafeTrustManager : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return emptyArray()
        }

    }

    inner class MyTrustManager(private var localTrustManager: X509TrustManager?) :
        X509TrustManager {

        private var defaultTrustManager: X509TrustManager? = null

        init {
            val trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as? KeyStore)
            defaultTrustManager = chooseTrustManager(trustManagerFactory.trustManagers)
        }

        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            try {
                defaultTrustManager?.checkServerTrusted(chain, authType)
            } catch (e: CertificateException) {
                localTrustManager?.checkServerTrusted(chain, authType)
            }
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return emptyArray()
        }

    }
}