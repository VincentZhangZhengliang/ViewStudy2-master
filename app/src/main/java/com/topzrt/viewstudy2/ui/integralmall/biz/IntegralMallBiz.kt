package com.topzrt.viewstudy2.ui.integralmall.biz

import android.content.Context
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.integralmall.bean.DoChangeBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */
class IntegralMallBiz : IIntegralMallBiz {

    /**
     * 积分兑换
     */
    override fun doChange(context: Context, _Sessionkey: String, goods_id: String, memo: String, user_name: String, user_phone: String, user_address: String): Observable<DoChangeBean> {
        return ReApiImpl.doChange(context, _Sessionkey, goods_id, memo, user_name, user_phone, user_address).subscribeOn(Schedulers.io())
    }

    override fun onRefresh(context: Context, _Sessionkey: String): Observable<IntegralMallBean> {
        return ReApiImpl.scoreRedbag(context, _Sessionkey).subscribeOn(Schedulers.io())
    }


    override fun scoreRedbag(context: Context, _Sessionkey: String): Observable<IntegralMallBean> {
        return ReApiImpl.scoreRedbag(context, _Sessionkey).subscribeOn(Schedulers.io())
    }


}