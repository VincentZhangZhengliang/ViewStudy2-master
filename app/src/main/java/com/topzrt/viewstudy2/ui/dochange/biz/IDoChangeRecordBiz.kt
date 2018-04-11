package com.topzrt.viewstudy2.ui.dochange.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/24;
 * DSC:
 */
interface IDoChangeRecordBiz {

    fun getData(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean>

    fun onRefresh(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean>

    fun onLoadmore(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean>

}