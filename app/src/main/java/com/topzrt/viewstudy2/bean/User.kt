package com.topzrt.viewstudy2.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Vincent;
 * Created on 2017/12/21;
 * DSC:
 */

data class User(@SerializedName("_Sessionkey") var _Sessionkey: String, var cid: String,
                var gestures_passwd: String, var gestures_status: String, var idcardpassed: String,
                var levels: String, var mobile: String, var no_read_mess: String,
                var response_code: String, var returns: String, var show_err: String,
                var title: String, var uid: String, var user_login_status: String,
                var user_money: String, var user_money_format: String,
                var user_name: String) : Serializable