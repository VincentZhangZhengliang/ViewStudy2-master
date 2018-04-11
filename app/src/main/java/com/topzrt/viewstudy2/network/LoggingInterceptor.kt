package com.topzrt.viewstudy2.network

import com.topzrt.viewstudy2.utils.L
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
        L.e(request.toString())
//        KLog.d(String.format("Sending request %s on %s%n%s", request?.url(), chain?.connection(), request?.headers()))
        val t1 = System.nanoTime()
        val response = chain?.proceed(chain.request())
        val t2 = System.nanoTime()
        L.e(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",response?.request()?.url(), (t2 - t1) / 1e6, response?.headers()))
//        KLog.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response?.request()?.url(), (t2 - t1) / 1e6, response?.headers()))
        val mediaType = response?.body()!!.contentType()
        val content = response.body()!!.string()
//        KLog.json(content)
        L.e(content)
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build()
    }
}