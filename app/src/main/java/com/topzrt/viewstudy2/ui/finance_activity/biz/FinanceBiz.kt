package com.topzrt.viewstudy2.ui.finance_activity.biz

import android.content.Context
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.finance_activity.bean.CatTypeListBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListBean
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */
class FinanceBiz : IFinanceBiz {

    override fun onLoadMore(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean> {
        return ReApiImpl.dealslist(context, _Sessionkey, p, loan_type).subscribeOn(Schedulers.io())
    }

    override fun onFresh(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean> {
        return ReApiImpl.dealslist(context, _Sessionkey, p, loan_type).subscribeOn(Schedulers.io())
    }

    override fun getData(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<Pair<CatTypeListBean, DealsListBean>> {
        val ob1 = ReApiImpl.catTypeList(context).subscribeOn(Schedulers.io())
        val ob2 = ReApiImpl.dealslist(context, _Sessionkey, p, loan_type).subscribeOn(Schedulers.io())
        return Observable.zip(ob1, ob2, BiFunction<CatTypeListBean, DealsListBean, Pair<CatTypeListBean, DealsListBean>> { t1, t2 -> Pair(t1, t2) })
    }

    override fun dealsList(context: Context, _Sessionkey: String, p: Int, loan_type: Int): Observable<DealsListBean> {
        return ReApiImpl.dealslist(context, _Sessionkey, p, loan_type).subscribeOn(Schedulers.io())
    }

    override fun catTypeList(context: Context): Observable<CatTypeListBean> {
        return ReApiImpl.catTypeList(context).subscribeOn(Schedulers.io())
    }

}