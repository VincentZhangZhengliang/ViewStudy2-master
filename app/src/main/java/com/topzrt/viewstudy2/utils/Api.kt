package com.topzrt.viewstudy2.utils

import com.topzrt.viewstudy2.app.MyApplication
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by Vincent;
 * Created on 2017/11/27;
 * DSC: 网络接口  通过callback返回结果
 */
class Api {

    companion object {

        fun get_Sign(params: HashMap<String, String?>): String {
            params["_Timestamp"] = "" + System.currentTimeMillis() / 1000
            params["_Platform"] = "android"
            params["_Uniquecode"] = MyApplication.instance.getImei()
            params["tid"] = Constants.TID
            params["is_pc"] = "2"
            val keySet = params.keys
            val list = mutableListOf<String>()
            list.addAll(keySet)
            list.sort()
            val sb = StringBuffer()
            for (i in list.indices) {
                if (i == 0) {
                    sb.append(list[i]).append("=").append(params[list[i]])
                } else {
                    sb.append("&").append(list[i]).append("=").append(params[list[i]])
                }
            }
            sb.append("&key=b178babb9bf0770fd71de7b85797ff25")
            L.e(msg = sb.toString())
            return MD5Util.getMD5(sb.toString())
        }

        fun getIp(): String? {
            var ip: String
            var inStream: InputStream
            try {
                val infoUrl = URL("http://2017.ip138.com/ic.asp")
                val connection = infoUrl.openConnection()
                val httpConnection = connection as HttpURLConnection
                val responseCode = httpConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inStream = httpConnection.inputStream
                    val reader = BufferedReader(InputStreamReader(inStream, "gb2312"))
                    val builder = StringBuilder()
                    var line: String
                    while (true) {
                        line = reader.readLine() ?: break
                        builder.append(line)
                    }
                    inStream.close()
                    val start = builder.indexOf("[")
                    val end = builder.indexOf("]")
                    ip = builder.substring(start + 1, end)
                    return ip
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }


        /**
         * 启动页
         */
        fun qiDongye(callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_QI_DONG_YE).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").build().execute(callback)
        }

        /**
         * 登录
         */
        fun login(mobile: String, pwd: String, callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            map.put("mobile", mobile)
            map.put("pwd", pwd)
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_LOGIN).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").addParams("mobile", mobile).addParams("pwd", pwd).build().execute(callback)
        }

        /**
         * 注册
         */
        fun register(mobile: String, user_pwd: String, user_pwd_confirm: String, mobile_code: String, referer: String, callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            map.put("mobile", mobile)
            map.put("user_pwd", user_pwd)
            map.put("user_pwd_confirm", user_pwd_confirm)
            map.put("mobile_code", mobile_code)
            map.put("referer", referer)
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_REGISTER).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").addParams("mobile", mobile).addParams("user_pwd", user_pwd).addParams("user_pwd_confirm", user_pwd_confirm).addParams("mobile_code", mobile_code).addParams("referer", referer).build().execute(callback)
        }

        /**
         * 获取注册验证码
         */
        fun getRegisterVerifyCode(ip: String, mobile: String, callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            map.put("mobile", mobile)
            map.put("client_ip", ip)
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_SEND_REGISTER_CODE).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").addParams("mobile", mobile).addParams("client_ip", ip).build().execute(callback)
        }

        /**
         * 首页banner
         */
        fun homeBanner(callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_BANNER).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").build().execute(callback)
        }

        /**
         *  首页理财推荐
         */
        fun recommendationData(callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_LICAI_RECOMMENDATION).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").build().execute(callback)
        }

        /**
         * 新手专区
         */
        fun noviceZone(callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_NOVICE_ZONE).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").build().execute(callback)
        }

        /**
         * 公告列表
         */
        fun getNotice(p: String, callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            map.put("p", p)
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_NOTICE).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").addParams("p", p).build().execute(callback)
        }

        /**
         * 公告详情
         */
        fun getNoticeDetail(id: String, callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            map.put("id", id)
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_SHOW_ARTICLE).addParams("id", id).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").build().execute(callback)
        }

        /**
         * 获取h5页面
         * type : 1-公司简介 2-安全保障3-补签说明4-兑换须知5-专家顾问6-风险提示书7-安全保障8-理财经理9-管理团队10-充值注意事项11-提现注意事项12-等额本息13-一次性还本+收益14-每月收益到期还本
         */
        fun getH5(type: String, callback: StringCallback) {
            /**    获取_Sign      */
            val map = HashMap<String, String?>()
            map.put("type", type)
            val _Sign = get_Sign(map)
            OkHttpUtils.post().url(HttpUrls.URL_ADDRE_GET_H5).addParams("type", type).addParams("_Sessionkey", "").addParams("_Timestamp", "" + System.currentTimeMillis() / 1000).addParams("_Platform", "android").addParams("_Uniquecode", MyApplication.instance.getImei()).addParams("_Sign", _Sign).addParams("tid", Constants.TID).addParams("is_pc", "2").build().execute(callback)
        }

        fun posthttps(callback: StringCallback) {
            OkHttpUtils.post()
                    .url("https://bank.topzrt.com:90/terminal/user1/accountbalance")
//                    .url("https://www.topzrt.com/")
                    .addParams("userid", "206471")
                    .build().execute(callback)

//            OkHttpUtils.get()
//                    .url("https://bank.topzrt.com:90/terminal/user/accountbasic?userid=206471")
//                    .build().execute(callback)
        }
    }
}