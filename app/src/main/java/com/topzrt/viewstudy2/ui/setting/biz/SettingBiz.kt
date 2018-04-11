package com.topzrt.viewstudy2.ui.setting.biz

import android.content.Context
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.setting.listener.OnLogOutListener
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import com.topzrt.viewstudy2.utils.FileUtils
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
class SettingBiz : ISettingBiz {

    /**
     * 获取华兴开关状态
     */
    override fun getHxcgStatus(context: Context): Observable<HxcgStatusBean> {
        return ReApiImpl.getHxcgStatus(context).subscribeOn(Schedulers.io())
    }

    /**
     * 获取用户中心的数据
     */
    override fun ucCenter(context: Context, _Sessionkey: String): Observable<UcCenterBean> {
        return ReApiImpl.ucCenter(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

    /**
     * 获取华兴状态和用户中心的数据
     */
    override fun getData(context: Context, _Sessionkey: String): Observable<Pair<HxcgStatusBean, UcCenterBean>> {
        return Observable.zip(getHxcgStatus(context), ucCenter(context, _Sessionkey), BiFunction<HxcgStatusBean, UcCenterBean, Pair<HxcgStatusBean, UcCenterBean>> { t1, t2 -> Pair(t1, t2) })
    }

    /**
     * 退出登录
     */
    override fun logOut(context: Context,onLogOutListener: OnLogOutListener) {
        MyApplication.instance.setUser(null)
        FileUtils.saveLogin(context, null)
        onLogOutListener.logOutSuccess()
    }

}