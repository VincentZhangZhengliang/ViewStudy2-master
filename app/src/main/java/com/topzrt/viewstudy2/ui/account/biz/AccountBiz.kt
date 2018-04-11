package com.topzrt.viewstudy2.ui.account.biz

import android.content.Context
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBalanceBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
class AccountBiz : IAccountBiz {

    override fun hxAccountBalance(context: Context, userid: String, appid: String): Observable<HxAccountBalanceBean> {
        return ReApiImpl.hxAccountBalance(context, userid, appid).subscribeOn(Schedulers.io())
    }

    override fun onRefresh(context: Context, userid: String, _Sessionkey: String): Observable<Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>> {
        return getData(context, userid, _Sessionkey)
    }

    override fun onLoadMore(context: Context, userid: String, _Sessionkey: String): Observable<Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>> {
        return getData(context, userid, _Sessionkey)
    }

    override fun getHxcgStatus(context: Context): Observable<HxcgStatusBean> {
        return ReApiImpl.getHxcgStatus(context).subscribeOn(Schedulers.io())
    }

    override fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean> {
        return ReApiImpl.ucCenter(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

    override fun hxAccountBasic(context: Context, userid: String, appid: String): Observable<HxAccountBasicBean> {

        return ReApiImpl.hxAccountBasic(context, userid, appid).subscribeOn(Schedulers.io())
    }

    //    override fun getData(context: Context, userid: String, _Sessionkey: String): Observable<Pair<UcCenterBean, HxAccountBasicBean>> {
    override fun getData(context: Context, userid: String, _Sessionkey: String): Observable<Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>> {
        //华兴存管开关
        val ob1 = ReApiImpl.getHxcgStatus(context).subscribeOn(Schedulers.io())
        //用户中心数据
        val ob2 = ReApiImpl.ucCenter(context, _Sessionkey).subscribeOn(Schedulers.io())
        //用户华兴账户信息
        val ob3 = ReApiImpl.hxAccountBasic(context, userid).subscribeOn(Schedulers.io())
        //用户华兴账户余额
        val ob5 = ReApiImpl.hxAccountBalance(context, userid).subscribeOn(Schedulers.io())

        val ob6 = ob1.flatMap { t ->
            if (t.data.param_value == 1) {
                Observable.zip(ob5, ob3, BiFunction<HxAccountBalanceBean, HxAccountBasicBean, Pair<HxAccountBalanceBean, HxAccountBasicBean>> { t1, t2 -> Pair(t1, t2) })
            } else {
                Observable.error(object : Throwable(t.msg) {})
            }
        }
        //根据华兴存管的状态来加载华兴用户账户信息
        val ob4 = ob1.flatMap { t ->
            if (t.data.param_value == 1) ob3
            else Observable.error(object : Throwable(t.msg) {})
        }

        //将用户中心的数据和华兴存管的数据整合在一起
        return Observable.zip(ob2, ob6, BiFunction<UcCenterBean, Pair<HxAccountBalanceBean, HxAccountBasicBean>, Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>> { t1, t2 -> Triple(t1, t2.first, t2.second) })
//        return Observable.zip(ob2, ob4, BiFunction<UcCenterBean, HxAccountBasicBean, Pair<UcCenterBean, HxAccountBasicBean>> { t1, t2 -> Pair(t1, t2) })
    }


}