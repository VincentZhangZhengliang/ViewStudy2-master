package com.topzrt.viewstudy2.ui.dochange.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.dochange.biz.DoChangeRecordBiz
import com.topzrt.viewstudy2.ui.dochange.view.IDoChangeRecordView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/1/24;
 * DSC:
 */
class DoChangeRecordPresenter(var view: IDoChangeRecordView) {

    val biz = DoChangeRecordBiz()

    fun recordChange(context: Context, _Sessionkey: String, p: Int) {
        biz.getData(context, _Sessionkey, p).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status != 1) {
                view.toast(it.msg)
            } else {
                view.setData(it)
            }
        }
    }

    fun onRefresh(context: Context, _Sessionkey: String, p: Int) {
        biz.onRefresh(context, _Sessionkey, p).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status != 1) {
                view.toast(it.msg)
            } else {
                view.onRefresh(it)
            }
        }
    }


    fun onLoadmore(context: Context, _Sessionkey: String, p: Int) {
        biz.onLoadmore(context, _Sessionkey, p).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status != 1) {
                view.toast(it.msg)
            } else {
                view.onLoadmore(it)
            }
        }
    }
}