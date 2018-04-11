package com.topzrt.viewstudy2.ui.safe_guarantee.biz

import android.content.Context
import com.topzrt.viewstudy2.bean.H5Bean
import com.topzrt.viewstudy2.network.ReApiImpl
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/30;
 * DSC:
 */
class SafeGuaranteeBiz:ISafeGuaranteeBiz {

    override fun get_h5(context: Context, type: Int):Observable<H5Bean> {
        return ReApiImpl.get_h5(context, type).subscribeOn(Schedulers.io())
    }

}