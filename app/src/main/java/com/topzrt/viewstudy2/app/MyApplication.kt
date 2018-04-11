package com.topzrt.viewstudy2.app

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.Toast
import com.meituan.android.walle.WalleChannelReader
import com.topzrt.viewstudy2.accountService.DefaultAccountService
import com.topzrt.viewstudy2.accountService.SIDTokenListener
import com.topzrt.viewstudy2.bean.User
import com.topzrt.viewstudy2.utils.FileUtils
import com.topzrt.viewstudy2.utils.L
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.cookie.CookieJarImpl
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore
import com.zhy.http.okhttp.https.HttpsUtils
import com.zhy.http.okhttp.log.LoggerInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Created by Vincent;
 * Created on 2017/11/27;
 * DSC:
 */
class MyApplication : Application() {

    var mUser: User? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()
        setUser(FileUtils.getLogin(this))
        initOkHttp()
        val channel = WalleChannelReader.getChannel(this.applicationContext)
        L.e("channel : $channel")
    }

    /**
     * 配置Okhttp
     */
    private fun initOkHttp() {
        //cookiejar方法配置  PersistentCookieStore(持久化)    SerializableHttpCookie(持久化)  MemoryCookieStore(信息存在内存中)
        val cookieJar = CookieJarImpl(PersistentCookieStore(applicationContext))
        //设置可访问所有的https网站
//        val sslParams = HttpsUtils.getSslSocketFactory(null, null, null)
        //设置具体的证书 :
        val sslParams = HttpsUtils.getSslSocketFactory(arrayOf(assets.open("2_root_bundle.crt")), null, "123")
        //HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputstream, null, null);

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggerInterceptor("Vincent"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager).readTimeout(10000L, TimeUnit.MILLISECONDS).cookieJar(cookieJar)
                //其他配置
                .build()

        OkHttpUtils.initClient(okHttpClient)
    }

    companion object {
        val instance = MyApplication()
    }


    /**
     * @return the User singleton instance
     */
    fun getUser(): User? {
        return mUser
    }

    /**
     * 重置user
     *
     * @param user
     */
    fun setUser(user: User?) {
        mUser = user
    }

    @SuppressLint("MissingPermission")
    fun getImei(): String {
        //        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        //        val DEVICE_ID = tm.deviceId
        //        return if (TextUtils.isEmpty(DEVICE_ID)) "no_find" else DEVICE_ID
        return "000000000000000"
    }


}