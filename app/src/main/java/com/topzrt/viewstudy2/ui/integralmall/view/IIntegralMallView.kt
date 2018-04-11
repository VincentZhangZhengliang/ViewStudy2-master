package com.topzrt.viewstudy2.ui.integralmall.view

import com.topzrt.viewstudy2.ui.integralmall.bean.DoChangeBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallBean

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */
interface IIntegralMallView {

    fun setData(integralMallBean: IntegralMallBean)

    fun refreshData(integralMallBean: IntegralMallBean)

    fun doChange(doChangeBean: DoChangeBean)
}