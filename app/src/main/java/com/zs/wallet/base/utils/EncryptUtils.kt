package com.zs.wallet.base.utils

import com.zs.lib.log.Logger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.HashMap

/**
 *
 * desc: 项目加密工具类
 * date: 2019-12-17 11:42
 */
class EncryptUtils {
    companion object {

        fun encryptParams(map: TreeMap<String, String>): Map<String, String> {
            val builder: StringBuilder = StringBuilder()
            map.forEach {
                builder.append(it.key)
                builder.append(it.value)
            }
            builder.append(AppConstant.SECRET)
            Logger.i(" 签名之前：===$builder")
            map["sign"] = string2MD5(builder.toString())
            return map
        }

        fun encryptParams2Str(map: HashMap<String, String>): String {
            map["time"] = System.currentTimeMillis().toString()
            val builder: StringBuilder = StringBuilder()
            map.forEach {
                builder.append(it.key)
                builder.append(it.value)
            }
            builder.append(AppConstant.SECRET)
            Logger.i(" 签名之前：===$builder")
            return string2MD5(builder.toString())
        }

        //字符串转32位MD5
        fun string2MD5(text: String): String {
            var result = ""
            try {
                val md = MessageDigest.getInstance("MD5")
                md.update(text.toByteArray())
                val b = md.digest()
                var i: Int
                val buf = StringBuffer("")
                for (offset in b.indices) {
                    i = b[offset].toInt()
                    if (i < 0)
                        i += 256
                    if (i < 16)
                        buf.append("0")
                    buf.append(Integer.toHexString(i))
                }
                result = buf.toString()
            } catch (e: NoSuchAlgorithmException) {
                println(e)
            }
            Logger.i(" 签名结果：===$result")
            return result
        }
    }
}