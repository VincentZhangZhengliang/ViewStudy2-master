package com.topzrt.viewstudy2.bean

import java.io.Serializable

/**
 * Created by Vincent;
 * Created on 2017/12/22;
 * DSC:
 */


data class LoginBean(var status: Int, var msg: String, var data: User) : Serializable