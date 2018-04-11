package com.topzrt.viewstudy2.utils

import android.annotation.SuppressLint
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import java.util.regex.Pattern


/**
 * Created by Vincent;
 * Created on 2017/11/27;
 * DSC:
 */
class CommonUtils {


    companion object {

        fun isPhoneLegal(number: String?): Boolean {
            val rex = "^1[3|4|5|7|8][0-9]\\d{8}$"
            val pattern = Pattern.compile(rex)
            val matcher = pattern.matcher(number)
            return matcher.matches()

        }

        fun isPasswordLegal(password: String?): Boolean {
            val rex = "^(?![0-9]+\$)(?![a-zA-Z]+\$)[0-9A-Za-z]{8,20}\$"
            val pattern = Pattern.compile(rex)
            val matcher = pattern.matcher(password)
            return matcher.matches()
        }


        @SuppressLint("NewApi") private fun getSoftButtonsBarHeight(
                activity: AppCompatActivity): Int {
            val metrics = DisplayMetrics()
            //这个方法获取可能不是真实屏幕的高度
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            val usableHeight = metrics.heightPixels
            //获取当前屏幕的真实高度
            activity.windowManager.defaultDisplay.getRealMetrics(metrics)
            val realHeight = metrics.heightPixels
            return if (realHeight > usableHeight) {
                realHeight - usableHeight
            } else {
                0
            }
        }

        fun isSoftShowing(activity: AppCompatActivity): Boolean {
            //获取当前屏幕内容的高度
            val screenHeight = activity.window.decorView.height
            //获取View可见区域的bottom
            val rect = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(rect)
            return screenHeight - rect.bottom - getSoftButtonsBarHeight(activity) != 0
        }




    }

}