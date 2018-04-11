package com.topzrt.viewstudy2.ui.BbinIncome.biz

import android.content.Context
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
class BbinIncomeBiz : IBbinIncomeBiz {

    override fun showNewDeal(context: Context, _Sessionkey: String): Observable<BbinIncomeBean> {
        return ReApiImpl.showNewDeal(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

    override fun onRefresh(context: Context, _Sessionkey: String): Observable<BbinIncomeBean> {
        return ReApiImpl.showNewDeal(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

    override fun onLoadMore(context: Context, _Sessionkey: String): Observable<BbinIncomeBean> {
        return ReApiImpl.showNewDeal(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

}