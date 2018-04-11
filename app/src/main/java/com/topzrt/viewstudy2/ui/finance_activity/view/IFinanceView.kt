package com.topzrt.viewstudy2.ui.finance_activity.view

import com.topzrt.viewstudy2.ui.finance_activity.bean.CatTypeListDataBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListDataBean

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */
interface IFinanceView {

    fun toast(msg: String)

    fun setCatType(data: CatTypeListDataBean)

    fun setDealsList(data: DealsListDataBean)

    fun onFresh(data: DealsListDataBean)

    fun onLoadMore(data: DealsListDataBean)
}