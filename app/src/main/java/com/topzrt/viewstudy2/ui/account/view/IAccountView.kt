package com.topzrt.viewstudy2.ui.account.view

import com.topzrt.viewstudy2.ui.account.bean.HxAccountBalanceBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
interface IAccountView {

    fun showLoading()

    fun closeLoading()

    fun setData(data: Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>)

    fun refreshData(refreshData: Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>)

    fun loadMoreData(loadMoreData: Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>)

    fun toast(msg:String)

}