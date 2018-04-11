package com.topzrt.viewstudy2.ui.finance_activity.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.finance_activity.bean.CatTypeListBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */
interface IFinanceBiz {

    fun catTypeList(context: Context): Observable<CatTypeListBean>

    fun dealsList(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean>

    fun getData(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<Pair<CatTypeListBean, DealsListBean>>

    fun onFresh(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean>

    fun onLoadMore(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean>
}