package com.topzrt.viewstudy2.network

import android.content.Context
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.bean.*
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBalanceBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.CatTypeListBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListBean
import com.topzrt.viewstudy2.ui.integralmall.bean.DoChangeBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallBean
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import com.topzrt.viewstudy2.ui.signup.bean.ContinuousSignUpBean
import com.topzrt.viewstudy2.ui.signup.bean.IntegralListBean
import com.topzrt.viewstudy2.ui.signup.bean.SignUpBean
import com.topzrt.viewstudy2.utils.Constants
import com.topzrt.viewstudy2.utils.HttpUrls
import com.topzrt.viewstudy2.utils.L
import com.topzrt.viewstudy2.utils.MD5Util
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import io.reactivex.Observable
import okhttp3.MultipartBody
import java.util.*

/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
object ReApiImpl {

    /**
     * 获取丰付接口签名
     */
    fun getSign(map: LinkedHashMap<String, String>): String {
        map.put("_Timestamp", "" + System.currentTimeMillis() / 1000)
        map.put("_Platform", "android")
        map.put("_Uniquecode", MyApplication.instance.getImei())
        map.put("tid", Constants.TID)
        map.put("is_pc", "2")
        val sb = StringBuffer()
        map.entries.forEach { sb.append(it.key).append("=").append(it.value).append("&") }
        sb.append("key=b178babb9bf0770fd71de7b85797ff25")
        L.d(msg = sb.toString())
        return MD5Util.getMD5(sb.toString())
    }

    /**
     * 获取华兴接口签名
     */
    private fun getHxSign(map: Map<String, String>): String {
        val sb = StringBuffer()
        map.values.forEach {
            sb.append(it)
        }
        sb.append(Constants.HX_KEY)
        return MD5Util.getMD5(sb.toString())
    }


    /**
     * 获取外网IP
     */
    fun getIp(callback: StringCallback) {
        OkHttpUtils.get().url(HttpUrls.URL_IP).build().execute(callback)
    }

    /**
     * 登录
     */
    fun login(context: Context, mobile: String, pwd: String): Observable<LoginBean> {
        val map = TreeMap<String, String>()
        map.put("mobile", mobile)
        map.put("pwd", pwd)
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        L.d("Vincent", "login dddddddddddddd")
        return ReApiUtils.getInstance(context).login(filterValues)
    }

    /**
     * 注册
     */
    fun register(context: Context, mobile: String, user_pwd: String, user_pwd_confirm: String, mobile_code: String, referer: String): Observable<RegisterBean> {
        val map = TreeMap<String, String>()
        map.put("mobile", mobile)
        map.put("user_pwd", user_pwd)
        map.put("user_pwd_confirm", user_pwd_confirm)
        map.put("mobile_code", mobile_code)
        map.put("referer", referer)
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).register(filterValues)
    }

    /**
     * 首页Banner
     */
    fun banner(context: Context, _Sessionkey: String): Observable<HomeBannerBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        if (_Sessionkey.isNotEmpty()) filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).banner(filterValues)
    }

    /**
     * 启动页
     */
    fun qiDongYe(context: Context, _Sessionkey: String): Observable<QiDongYeBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        if (_Sessionkey.isNotEmpty()) filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).qiDongYe(filterValues)
    }

    /**
     * 理财推荐
     */
    fun licaiRecommendation(context: Context, _Sessionkey: String): Observable<RecommendationBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        if (_Sessionkey.isNotEmpty()) filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).licaiRecommendation(filterValues)
    }

    /**
     * 发送验证码
     */
    fun sendRegisterCode(context: Context, _Sessionkey: String, mobile: String, client_ip: String): Observable<VerifyCodeBean> {
        val map = TreeMap<String, String>()
        map.put("mobile", mobile)
        map.put("client_ip", client_ip)
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        if (_Sessionkey.isNotEmpty()) filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).sendRegisterCode(filterValues)
    }

    /**
     * 用户中心
     */
    fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        if (_Sessionkey.isNotEmpty()) filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).ucCenter(filterValues)
    }

    /**
     * 获取华兴存管的状态
     */
    fun getHxcgStatus(context: Context): Observable<HxcgStatusBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", "")
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).getHxcgStatus(filterValues)
    }

    /**
     * 积分商城
     */
    fun scoreRedbag(context: Context, _Sessionkey: String): Observable<IntegralMallBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).scoreRedbag(filterValues)
    }

    /**
     * 积分兑换
     */

    fun doChange(context: Context, _Sessionkey: String, goods_id: String, memo: String, user_name: String, user_phone: String, user_address: String): Observable<DoChangeBean> {
        val map = TreeMap<String, String>()
        map.put("goods_id", goods_id)
        map.put("memo", memo)
        map.put("user_name", user_name)
        map.put("user_phone", user_phone)
        map.put("user_address", user_address)
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).doChange(filterValues)
    }

    /**
     * jifen兑换记录
     */
    fun recordChange(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean> {
        val map = TreeMap<String, String>()
        map.put("p", p.toString())
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).recordChange(filterValues)
    }

    /**
     * 签到
     */
    fun ucScoreSignIn(context: Context, _Sessionkey: String): Observable<SignUpBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).ucScoreSignIn(filterValues)
    }

    /**
     * 连续签到
     */
    fun receive(context: Context, _Sessionkey: String, gift_type: String): Observable<ContinuousSignUpBean> {
        val map = TreeMap<String, String>()
        map.put("gift_type", gift_type)
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).receive(filterValues)
    }

    /**
     * 签到日历
     */
    fun integralList(context: Context, _Sessionkey: String): Observable<IntegralListBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).integralList(filterValues)
    }

    /**
     * 体验标收益
     */
    fun showNewDeal(context: Context, _Sessionkey: String): Observable<BbinIncomeBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).showNewDeal(filterValues)
    }

    /**
     * 标的分类和类型
     */
    fun catTypeList(context: Context): Observable<CatTypeListBean> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).catTypeList(filterValues)
    }

    /**
     *理财列表
     */
    fun dealslist(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean> {
        val map = TreeMap<String, String>()
        map.put("loan_type", loan_type.toString())
        map.put("cate_id", "-1")
        map.put("repay_time", "-1")
        map.put("deal_status", "-1")
        map.put("p", p.toString())
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", _Sessionkey)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).dealslist(filterValues)
    }

    /**
     * 获取H5链接
     * 1-公司简介 2-安全保障3-补签说明4-兑换须知5-专家顾问6-风险提示书7-安全保障8-理财经理9-管理团队10-充值注意事项11-提现注意事项
     */
    fun get_h5(context: Context, type: Int): Observable<H5Bean> {
        val map = TreeMap<String, String>()
        map.put("type", type.toString())
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).get_h5(filterValues)
    }

    /**
     * 华兴用户基本信息
     */
    fun hxAccountBasic(context: Context, userid: String, appid: String = "APP"): Observable<HxAccountBasicBean> {
        val treemap = TreeMap<String, String>()
        treemap.put("userid", userid)
        treemap.put("appid", appid)
        val sign = getHxSign(treemap)
        return ReApiUtils.getInstance(context, HttpUrls.HX_URL).hxAccountBasic(userid, appid, sign)
    }

    fun hxAccountBalance(context: Context, userid: String, appid: String = "APP"): Observable<HxAccountBalanceBean> {
        val treemap = TreeMap<String, String>()
        treemap.put("userid", userid)
        treemap.put("appid", appid)
        val sign = getHxSign(treemap)
        return ReApiUtils.getInstance(context, HttpUrls.HX_URL).hxAccountBalance(userid, appid, sign)
    }

    /**
     * 上传图片
     */
    fun uploadPic(context: Context, file: MultipartBody.Part): Observable<String> {
        val map = TreeMap<String, String>()
        val filterValues = map.filterValues { it.isNotEmpty() } as LinkedHashMap
        val _Sign = getSign(filterValues)
        filterValues.put("_Sessionkey", "")
        filterValues.put("_Sign", _Sign)
        L.d(filterValues.toString())
        return ReApiUtils.getInstance(context).uploadPic(filterValues, file)
    }

}