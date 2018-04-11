package com.topzrt.viewstudy2.ui.BbinIncome.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.BbinIncome.biz.BbinIncomeBiz
import com.topzrt.viewstudy2.ui.BbinIncome.view.IBbinIncomeView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
class BbinIncomePresenter(var iView: IBbinIncomeView) {

    val biz = BbinIncomeBiz()

    fun showNewDeal(context: Context, _Sessionkey: String) {
        biz.showNewDeal(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.setData(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun onRefresh(context: Context, _Sessionkey: String) {
        biz.onRefresh(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.onRefresh(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun onLoadMore(context: Context, _Sessionkey: String) {
        biz.onLoadMore(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.onLoadMore(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

}