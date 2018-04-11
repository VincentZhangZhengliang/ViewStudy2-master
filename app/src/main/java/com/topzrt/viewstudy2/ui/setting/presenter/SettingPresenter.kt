package com.topzrt.viewstudy2.ui.setting.presenter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import com.topzrt.viewstudy2.ui.setting.biz.ISettingBiz
import com.topzrt.viewstudy2.ui.setting.biz.SettingBiz
import com.topzrt.viewstudy2.ui.setting.listener.OnLogOutListener
import com.topzrt.viewstudy2.ui.setting.view.ISettingView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
class SettingPresenter(var iSettingView: ISettingView) {

    var settingBiz: ISettingBiz = SettingBiz()

    /**
     * 退出登录
     */
    fun logOut(context: Context, onLogOutListener: OnLogOutListener) {
        settingBiz.logOut(context, onLogOutListener)
    }

    fun getData(context: Context, _Sessionkey: String) {

        settingBiz.getData(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
//            val hxcgStatusBean = it.first
//            val ucCenterBean = it.second
            if (it.second.status != 1) {
                iSettingView.toast(it.second.msg)
            } else {
                iSettingView.setData(it)
            }
        }

    }

    /**
     * 获取华兴开关状态
     */
    fun getHxcgStatus(context: Context): Observable<HxcgStatusBean> {
        return ReApiImpl.getHxcgStatus(context).subscribeOn(Schedulers.io())
    }

    /**
     * 获取用户中心的数据
     */
    fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean> {
        return ReApiImpl.ucCenter(context, _Sessionkey)
    }

    fun toNextActivity(activity: AppCompatActivity, clazz: Class<*>) {
        iSettingView.toNextActivity(activity, clazz)
    }


}