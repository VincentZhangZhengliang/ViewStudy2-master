package com.topzrt.viewstudy2.ui.signup.view

import com.topzrt.viewstudy2.ui.signup.bean.ContinuousSignUpDataBean
import com.topzrt.viewstudy2.ui.signup.bean.IntegralListDataBean
import com.topzrt.viewstudy2.ui.signup.bean.SignUpBean

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
interface ISignUpView {

    fun signUp(signUpBean: SignUpBean)

    fun continuousSignUp(continuousSignUpDataBean: ContinuousSignUpDataBean)

    fun toast(msg: String)

    fun integralList(integralListDataBean: IntegralListDataBean)

}