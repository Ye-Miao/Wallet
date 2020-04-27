package com.zs.wallet.base.utils

import android.text.TextUtils
import com.tencent.mmkv.MMKV
import java.util.*

/**
 *
 * desc: 语言工具类
 * date: 2019-12-17 14:24
 */
class LanguageUtils {
    companion object {
        private const val SELECTED_LANGUAGE = "language_select"
        private val mmkv = MMKV.mmkvWithID("local_language")

        var systemCurrentLocal = Locale.getDefault()
        private var mSelectLanguage: String? = null

        fun saveLanguage(currentLan: String) {
            mmkv.encode(SELECTED_LANGUAGE, currentLan)
        }

        fun getSelectLanguage(): String {
            if (systemCurrentLocal.language.contains("zh")) {
                mSelectLanguage = mmkv.decodeString(SELECTED_LANGUAGE, "zh_CN")
                return mSelectLanguage!!
            }

            if (systemCurrentLocal.language.contains("en")) {
                mSelectLanguage = mmkv.decodeString(SELECTED_LANGUAGE, "en_US")
                return mSelectLanguage!!
            }

            if (systemCurrentLocal.language.contains("ko")) {
                mSelectLanguage = mmkv.decodeString(SELECTED_LANGUAGE, "ko_KR")
                return mSelectLanguage!!
            }

            if (systemCurrentLocal.language.contains("ja")) {
                mSelectLanguage = mmkv.decodeString(SELECTED_LANGUAGE, "ja_JP")
                return mSelectLanguage!!
            }

            // todo 后面结合业务添加
            var lan = ""
//            val languageBean = PublicInfoManager.getLanguage()
//            if (languageBean != null) {
//                lan = languageBean.defLan
//            }

            mSelectLanguage = if (TextUtils.isEmpty(lan)) {
                mmkv.decodeString(SELECTED_LANGUAGE, "en_US")
            } else {
                lan
            }
            return mSelectLanguage!!
        }

        fun isZh(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return mSelectLanguage == "zh" || mSelectLanguage == "zh_CN"
        }

        fun isMn(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return "mn_MN" == mSelectLanguage
        }

        fun isRussia(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return "ru_RU" == mSelectLanguage
        }

        fun isKorea(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return "ko_KR" == mSelectLanguage
        }

        fun isJapanese(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return "ja_JP" == mSelectLanguage
        }

        fun isVietNam(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return "vi_VN" == mSelectLanguage
        }

        fun isTW(): Boolean {
            if (TextUtils.isEmpty(mSelectLanguage)) {
                getSelectLanguage()
            }
            return "el_GR" == mSelectLanguage
        }

    }
}