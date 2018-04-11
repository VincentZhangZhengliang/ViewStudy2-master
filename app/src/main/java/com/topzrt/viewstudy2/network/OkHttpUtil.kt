package com.topzrt.viewstudy2.network

import android.content.Context
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
object OkHttpUtil {

    fun getClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(ReceivedCookiesInterceptor(context))
                .addInterceptor(AddCookiesInterceptor(context))
                .addInterceptor(LoggingInterceptor())
                .build()
    }

}