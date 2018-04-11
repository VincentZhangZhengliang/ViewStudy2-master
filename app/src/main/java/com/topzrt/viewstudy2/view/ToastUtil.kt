package com.topzrt.viewstudy2.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Vincent;
 * Created on 2017/12/27;
 * DSC:
 */
class ToastUtil(var context: Context, text: String, length: Int) {

    var toast: Toast

    init {
        toast = Toast.makeText(context, text, length)
    }

    fun addImg(res: Int): ToastUtil {
        val view = toast.view as LinearLayout
        val imageView = ImageView(context)
        imageView.setImageResource(res)
        view.addView(imageView)
        return this
    }

    fun setTextColor(textColor: Int): ToastUtil {
        val message = toast.view.findViewById<TextView>(android.R.id.message) as TextView
        message.setTextColor(textColor)
        return this
    }

    fun setBackgroundColor(res: Int): ToastUtil {
        toast.view.setBackgroundColor(res)
        return this
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN) fun setBackground(drawable: Drawable): ToastUtil {
        toast.view.background = drawable
        return this
    }

    fun setBackgroundResource(resid: Int): ToastUtil {
        toast.view.setBackgroundResource(resid)
        return this
    }

    fun show() {
        toast.show()
    }

}
