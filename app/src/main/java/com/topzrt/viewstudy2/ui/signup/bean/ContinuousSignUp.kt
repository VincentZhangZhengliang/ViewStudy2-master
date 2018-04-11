package com.topzrt.viewstudy2.ui.signup.bean

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */

data class ContinuousSignUpBean(var status: Int, var msg: String, var data: ContinuousSignUpDataBean)

data class ContinuousSignUpDataBean(var status: Int, var info: String, var score: Int)