package com.topzrt.viewstudy2.ui.account.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.account.biz.AccountBiz
import com.topzrt.viewstudy2.ui.account.view.IAccountView
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
class AccountPresenter(var iAccountView: IAccountView) {

    val accoutBiz = AccountBiz()

    fun getHxcgStatus(context: Context): Observable<HxcgStatusBean> {
        return accoutBiz.getHxcgStatus(context)
    }

    fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean> {
        return accoutBiz.ucCenter(context, _Sessionkey)
    }

    fun hxAccountBasic(context: Context, userid: String, appid: String): Observable<HxAccountBasicBean> {
        return accoutBiz.hxAccountBasic(context, userid, appid)
    }

    fun getData(context: Context, userid: String, _Sessionkey: String) {
        accoutBiz.getData(context, userid, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            val ucCenterBean = it.first
            if (ucCenterBean.status == 1) iAccountView.setData(it) else {
                iAccountView.toast(ucCenterBean.msg)
            }
        }
    }

    /**
     * 华兴账户余额
     */
    fun hxAccountBalance(context: Context, userid: String, appid: String) {
        accoutBiz.hxAccountBalance(context, userid, appid).observeOn(AndroidSchedulers.mainThread())
    }


    fun onRefresh(context: Context, userid: String, _Sessionkey: String) {
        accoutBiz.onRefresh(context, userid, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            val ucCenterBean = it.first
            if (ucCenterBean.status == 1) {
                iAccountView.refreshData(it)
            } else {
                iAccountView.toast(ucCenterBean.msg)
            }
        }
    }

    fun onLoadMore(context: Context, userid: String, _Sessionkey: String) {
        accoutBiz.onLoadMore(context, userid, _Sessionkey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            val ucCenterBean = it.first
            if (ucCenterBean.status == 1) {
                iAccountView.loadMoreData(it)
            } else {
                iAccountView.toast(ucCenterBean.msg)
            }
        }
    }


}