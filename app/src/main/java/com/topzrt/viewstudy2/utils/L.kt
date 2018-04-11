package com.topzrt.viewstudy2.utils

import android.util.Log

/**
 * Created by Vincent;
 * Created on 2017/11/28;
 * DSC: 打印日志
 */
class L {

    companion object {

        fun e(msg: String) {
            if (Constants.LOG_SW) e(tag = "Vincent", msg = msg)
        }

        fun e(tag: String, msg: String) {
            if (Constants.LOG_SW) Log.e(tag, msg)
        }

        fun d(msg: String) {
            if (Constants.LOG_SW) d(tag = "Vincent", msg = msg)
        }

        fun d(tag: String, msg: String) {
            if (Constants.LOG_SW) Log.d(tag, msg)
        }

    }

}