package com.topzrt.viewstudy2.ui.safe_guarantee.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.safe_guarantee.biz.SafeGuaranteeBiz
import com.topzrt.viewstudy2.ui.safe_guarantee.view.ISafeguaranteeView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/1/30;
 * DSC:
 */
class SafeGuaranteePresenter(var iView: ISafeguaranteeView) {

    val biz = SafeGuaranteeBiz()

    fun get_h5(context: Context, type: Int) {
        biz.get_h5(context, type).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.setData(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

}
