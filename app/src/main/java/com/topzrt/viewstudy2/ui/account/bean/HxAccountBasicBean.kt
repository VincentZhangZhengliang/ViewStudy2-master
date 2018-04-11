package com.topzrt.viewstudy2.ui.account.bean

/**
 * Created by Vincent;
 * Created on 2018/1/22;
 * DSC:
 */
data class HxAccountBasicBean(var status: String, var msg: String, var data: HxAccountBasicDataBean)

data class HxAccountBasicDataBean(var istest: Int, var usermobile: String, var username: String, var issecurity: Int, var iscertification: Int, var acno: String, var latecg: Int, var useremail: String, var realidno: String, var realname: String, var isbindcard: Int, var isghb: Int)

