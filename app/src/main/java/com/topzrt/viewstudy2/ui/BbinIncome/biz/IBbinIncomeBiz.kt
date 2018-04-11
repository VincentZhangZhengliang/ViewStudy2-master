package com.topzrt.viewstudy2.ui.BbinIncome.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
interface IBbinIncomeBiz {

    fun showNewDeal(context: Context, _Sessionkey: String): Observable<BbinIncomeBean>

    fun onRefresh(context: Context, _Sessionkey: String): Observable<BbinIncomeBean>

    fun onLoadMore(context: Context, _Sessionkey: String): Observable<BbinIncomeBean>

}