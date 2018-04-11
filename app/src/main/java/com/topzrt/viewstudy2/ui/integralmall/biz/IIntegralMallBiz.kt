package com.topzrt.viewstudy2.ui.integralmall.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.integralmall.bean.DoChangeBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */
interface IIntegralMallBiz {

    fun scoreRedbag(context: Context, _Sessionkey: String): Observable<IntegralMallBean>

    fun onRefresh(context: Context, _Sessionkey: String): Observable<IntegralMallBean>

//    param.add(new BasicNameValuePair("goods_id", goods_id));
//    param.add(new BasicNameValuePair("memo", memo));
//    param.add(new BasicNameValuePair("user_name", user_name));
//    param.add(new BasicNameValuePair("user_phone", user_phone));
//    param.add(new BasicNameValuePair("user_address", user_address));

    fun doChange(context: Context, _Sessionkey: String, goods_id: String, memo: String, user_name: String, user_phone: String, user_address: String): Observable<DoChangeBean>

}