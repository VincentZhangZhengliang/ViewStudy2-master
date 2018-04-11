package com.topzrt.viewstudy2.ui.account.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBalanceBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
interface IAccountBiz {

    fun getData(context: Context, userid: String, _Sessionkey: String): Observable<Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>>
    //华兴存管开关
    fun getHxcgStatus(context: Context): Observable<HxcgStatusBean>

    fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean>

    fun hxAccountBasic(context: Context, userid: String, appid: String): Observable<HxAccountBasicBean>

    fun onRefresh(context: Context, userid: String, _Sessionkey: String): Observable<Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>>

    fun onLoadMore(context: Context, userid: String, _Sessionkey: String): Observable<Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>>

    fun hxAccountBalance(context: Context, userid: String, appid: String): Observable<HxAccountBalanceBean>

}