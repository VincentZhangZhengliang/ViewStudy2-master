package com.topzrt.viewstudy2.ui.safe_guarantee.biz

import android.content.Context
import com.topzrt.viewstudy2.bean.H5Bean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/30;
 * DSC:
 */
interface ISafeGuaranteeBiz {

    fun get_h5(context: Context, type: Int): Observable<H5Bean>

}