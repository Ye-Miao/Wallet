package com.zs.wallet.main.api.model


import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @Author: Bertking
 * @Date：2018/12/24-8:41 PM
 * @Description:
 *
 * 	"klineScale": ["1min", "5min", "15min", "30min", "60min", "4h", "1day", "1week", "1month"],
 */
data class PublicInfoVo(
//        @SerializedName("coinList") val coinList: HashMap<String, CoinBean> = hashMapOf(),
//        @SerializedName("market") val market: HashMap<String, HashMap<String, CoinMapBean>> = hashMapOf(),
//        @SerializedName("app_logo_list") val appLogo: LogoBean = LogoBean("", "", "", ""),
//        @SerializedName("app_logo_list_new") val appNewLogo: NewLogoBean = NewLogoBean("", ""),
//        @SerializedName("lan") var lan: AppLanguageBean = AppLanguageBean(),AppLanguageBean
    @SerializedName("marketSort") val marketSort: ArrayList<String>? = arrayListOf(),
    @SerializedName("otcOpen") var otcOpen: String = "0",
    @SerializedName("depositOpen") var depositOpen: String = "0",
    @SerializedName("otcUrl") var otcUrl: String = "",
    @SerializedName("rate") var rate: TreeMap<String, TreeMap<String, String>> = TreeMap(),
    @SerializedName("bank_name_equal_auth") var bankNameEqualAuth: String = "0",
    @SerializedName("verificationType") var verificationType: String = "0",
    @SerializedName("is_enforce_google_auth") var isEnforceGoogleAuth: String = "0",
//    @SerializedName("kline_background_logo_img") var kline_logo: KLineLogo = KLineLogo(),
    @SerializedName("klineScale") var klineScale: Any = "",
    @SerializedName("app_help_center") var app_help_center: String = "",//配置帮助中心:如果字段为空，则使用默认帮助中心
    @SerializedName("otc_default_coin") var otc_default_coin: String = "",//场外默认交易币种
    @SerializedName("symbol_profile") var symbol_profile: String = "0",//是否开启币种简介
    @SerializedName("app_upload_img_type") var app_upload_img_type: String = "",//使用哪种方法上传图片  1使用token方式上传图片
    @SerializedName("coinsymbol_introduce_names") var coinsymbol_introduce_names: Any = "",//存在简介的币种列表
    @SerializedName("logo_white") var logo_white: String = "",//首页图片
//    @SerializedName("update_safe_withdraw") var update_safe_withdraw: SafeWithdrawBea = SafeWithdrawBea(
//        "",
//        ""
//    ),//首页图片
    @SerializedName("contractOpen") var contractOpen: String? = "0",
    @SerializedName("red_packet_open") var redPacketOpen: Int? = 0,
    @SerializedName("online_service_url") var onlineServiceUrl: String = ""//在线客服h5连接

)