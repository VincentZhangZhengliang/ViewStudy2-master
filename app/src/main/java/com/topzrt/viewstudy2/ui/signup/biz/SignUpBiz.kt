package com.topzrt.viewstudy2.ui.signup.biz

import android.content.Context
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.signup.bean.ContinuousSignUpBean
import com.topzrt.viewstudy2.ui.signup.bean.IntegralListBean
import com.topzrt.viewstudy2.ui.signup.bean.SignUpBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */

//{"status":1,"msg":"签到成功，您获得了5个积分！","errorCode":1}
class SignUpBiz : ISignUpBiz {

    override fun integralList(context: Context, _Sessionkey: String): Observable<IntegralListBean> {
        return ReApiImpl.integralList(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

    override fun signReward(context: Context, _Sessionkey: String, gift_type: String): Observable<ContinuousSignUpBean> {
        return ReApiImpl.receive(context, _Sessionkey, gift_type).subscribeOn(Schedulers.io())
    }

    override fun signUp(context: Context, _Sessionkey: String): Observable<SignUpBean> {
        return ReApiImpl.ucScoreSignIn(context, _Sessionkey).subscribeOn(Schedulers.io())
    }

}