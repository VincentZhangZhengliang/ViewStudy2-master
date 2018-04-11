package com.topzrt.viewstudy2.ui.BbinIncome.view

import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeDataBean

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
interface IBbinIncomeView {

    fun setData(bbinIncomeDataBean: BbinIncomeDataBean)

    fun onRefresh(bbinIncomeDataBean: BbinIncomeDataBean)

    fun onLoadMore(bbinIncomeDataBean: BbinIncomeDataBean)

    fun toast(msg: String)

}