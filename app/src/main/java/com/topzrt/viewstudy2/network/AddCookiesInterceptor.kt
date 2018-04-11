package com.topzrt.viewstudy2.network

import android.content.Context
import com.topzrt.viewstudy2.utils.SpUtils
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
class AddCookiesInterceptor(var context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val builder = chain?.request()?.newBuilder()
        val string = SpUtils.getString(context, "cookie", "")
        Observable.just(string).subscribe { t -> builder?.addHeader("Cookie", t) }
        return chain?.proceed(builder?.build())

    }
}