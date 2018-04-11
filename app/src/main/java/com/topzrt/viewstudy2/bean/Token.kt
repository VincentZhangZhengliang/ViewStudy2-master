package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/12/22;
 * DSC:    自定义的Token,   _SessionKey 应该拿到服务器返回的token来代替， expired：Token的有效期
 */
class Token(var uid: String, var _SessionKey: String, var expired: Long)