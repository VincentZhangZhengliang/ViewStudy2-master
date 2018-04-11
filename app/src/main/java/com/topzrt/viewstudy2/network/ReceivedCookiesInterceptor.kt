package com.topzrt.viewstudy2.network

import android.content.Context
import android.util.Log
import com.topzrt.viewstudy2.utils.L
import com.topzrt.viewstudy2.utils.SpUtils
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
class ReceivedCookiesInterceptor(var context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        val response = chain?.proceed(chain.request())
        val headers = response?.headers("Set-Cookie")
        L.d("headers : " + headers.toString())
        if (headers?.isNotEmpty() == true) {
            var stringBuilder = StringBuilder()
            Observable.fromIterable(headers).map { t ->
                val split = t.split(";")
                Log.d("Vincent", "ReceivedCookiesInterceptor : " + split[0])
                split[0]
            }.subscribe { stringBuilder.append(it).append(";") }

            SpUtils.saveString(context, "cookie", stringBuilder.toString())
        }
        return response!!
    }
}