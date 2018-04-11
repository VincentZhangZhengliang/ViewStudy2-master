package com.topzrt.viewstudy2.ui.integralmall.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.integralmall.biz.IntegralMallBiz
import com.topzrt.viewstudy2.ui.integralmall.view.IIntegralMallView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */
class IntegralMallPresenter(var iView: IIntegralMallView) {

    var integralBiz = IntegralMallBiz()

    fun scoreRedbag(context: Context, _Sessionkey: String) {
        integralBiz.scoreRedbag(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            iView.setData(it)
        }
    }

    fun onRefresh(context: Context, _Sessionkey: String) {
        integralBiz.onRefresh(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            iView.refreshData(it)
        }
    }

    fun doChange(context: Context, _Sessionkey: String, goods_id: String, memo: String, user_name: String, user_phone: String, user_address: String) {
        integralBiz.doChange(context, _Sessionkey, goods_id, memo, user_name, user_phone, user_address).observeOn(AndroidSchedulers.mainThread()).subscribe {
            iView.doChange(it)
        }
    }


}