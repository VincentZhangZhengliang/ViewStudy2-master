package com.topzrt.viewstudy2.ui.dochange.biz

import android.content.Context
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/24;
 * DSC:
 */
class DoChangeRecordBiz : IDoChangeRecordBiz {

    override fun onRefresh(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean> {
        return getData(context, _Sessionkey, p)
    }

    override fun onLoadmore(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean> {
        return getData(context, _Sessionkey, p)
    }

    override fun getData(context: Context, _Sessionkey: String, p: Int): Observable<DoChangeRecordBean> {
        return ReApiImpl.recordChange(context, _Sessionkey, p).subscribeOn(Schedulers.io())
    }


}