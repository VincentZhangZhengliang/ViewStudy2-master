package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2018/1/12;
 * DSC:
 */


//{"status":1,"msg":"注册成功","data":{"uid":"96000014","_Sessionkey":"55bc6149b8437f3cde14d0ce09196189"}}

data class RegisterDataBean(var uid: String, var _Sessionkey: String)

data class RegisterBean(var status: Int, var msg: String, var data: RegisterDataBean)