package com.topzrt.viewstudy2.ui.dochange.view

import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordBean

/**
 * Created by Vincent;
 * Created on 2018/1/24;
 * DSC:
 */
interface IDoChangeRecordView {

    fun setData(data: DoChangeRecordBean)

    fun onRefresh(data: DoChangeRecordBean)

    fun onLoadmore(data: DoChangeRecordBean)

    fun toast(msg: String)

}