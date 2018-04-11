package com.topzrt.viewstudy2.ui.finance_activity.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.finance_activity.biz.FinanceBiz
import com.topzrt.viewstudy2.ui.finance_activity.view.IFinanceView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */
class FinancePresenter(var iView: IFinanceView) {

    val biz = FinanceBiz()

    fun catTypeList(context: Context) {
        biz.catTypeList(context).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.setCatType(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun dealsList(context: Context, _Sessionkey: String, p: Int, loan_type: Int) {
        biz.dealsList(context, _Sessionkey, p, loan_type).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.setDealsList(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun getData(context: Context, _Sessionkey: String, p: Int, loan_type: Int) {
        biz.getData(context, _Sessionkey, p, loan_type).observeOn(AndroidSchedulers.mainThread()).subscribe {
            val catTypeListBean = it.first
            val dealsListBean = it.second
            if (catTypeListBean.status == 1) {
                iView.setCatType(catTypeListBean.data)
                if (dealsListBean.status == 1) {
                    iView.setDealsList(dealsListBean.data)
                } else {
                    iView.toast(dealsListBean.msg)
                }
            } else {
                iView.toast(catTypeListBean.msg)
            }
        }
    }

    fun onFresh(context: Context, _Sessionkey: String, p: Int, loan_type: Int) {
        biz.onFresh(context, _Sessionkey, p, loan_type).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.onFresh(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun onLoadMore(context: Context, _Sessionkey: String, p: Int, loan_type: Int) {
        biz.onFresh(context, _Sessionkey, p, loan_type).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.onLoadMore(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }


}

