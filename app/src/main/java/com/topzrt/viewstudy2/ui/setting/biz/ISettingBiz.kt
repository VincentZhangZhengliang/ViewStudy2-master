package com.topzrt.viewstudy2.ui.setting.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.setting.listener.OnLogOutListener
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
interface ISettingBiz {

    /**
     * 退出登录
     */
    fun logOut(context: Context, onLogOutListener: OnLogOutListener)

    /**
     * 获取华兴状态和用户中心的数据
     */
    fun getData(context: Context, _Sessionkey: String): Observable<Pair<HxcgStatusBean, UcCenterBean>>

    /**
     * 获取华兴开关状态
     */
    fun getHxcgStatus(context: Context): Observable<HxcgStatusBean>

    /**
     * 获取用户中心的数据
     */
    fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean>

    /**
     * 修改登录密码
     */


}