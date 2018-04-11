package com.topzrt.viewstudy2.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.topzrt.viewstudy2.utils.HttpUrls
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
object ReApiUtils {

    fun getInstance(context: Context, baseUrl: String = HttpUrls.URL_ADDRE): ReApi {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(OkHttpUtil.getClient(context)).build()
        return retrofit.create(ReApi::class.java)
    }

}

