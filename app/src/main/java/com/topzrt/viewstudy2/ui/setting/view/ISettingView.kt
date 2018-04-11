package com.topzrt.viewstudy2.ui.setting.view

import android.support.v7.app.AppCompatActivity
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
interface ISettingView {

    fun showLoading()

    fun closeLoading()

    fun toNextActivity(activity: AppCompatActivity, clazz: Class<*>)

    fun setData(data: Pair<HxcgStatusBean, UcCenterBean>)

    fun toast(msg: String)
}