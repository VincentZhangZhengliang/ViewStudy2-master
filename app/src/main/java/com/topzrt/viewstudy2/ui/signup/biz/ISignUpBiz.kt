package com.topzrt.viewstudy2.ui.signup.biz

import android.content.Context
import com.topzrt.viewstudy2.ui.signup.bean.ContinuousSignUpBean
import com.topzrt.viewstudy2.ui.signup.bean.IntegralListBean
import com.topzrt.viewstudy2.ui.signup.bean.SignUpBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
interface ISignUpBiz {

    fun signUp(context: Context, _Sessionkey: String): Observable<SignUpBean>

    fun signReward(context: Context, _Sessionkey: String, gift_type: String): Observable<ContinuousSignUpBean>

    fun integralList(context: Context, _Sessionkey: String): Observable<IntegralListBean>

}