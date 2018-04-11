package com.topzrt.viewstudy2.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vincent;
 * Created on 2017/12/27;
 * DSC:
 */
@SuppressLint("ShowToast")
public class ToastUtil {

    private Toast        toast;
    private LinearLayout toastView;

    public ToastUtil(Context context) {
        toast = new Toast(context);
    }

    /**
     * 完全自定义布局Toast
     *
     * @param context
     * @param view
     */
    public ToastUtil(Context context, View view, int duration) {
        toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(duration);
    }

    /**
     * 向Toast中添加自定义view
     *
     * @param view    添加的视图
     * @param postion 视图添加的位置
     * @return
     */
    public ToastUtil addView(View view, int postion) {
        toastView = (LinearLayout) toast.getView();
        toastView.addView(view, postion);
        return this;
    }

    /**
     * 设置Toast字体及背景颜色
     *
     * @param messageColor 字的颜色
     * @return
     */
    public ToastUtil setTextColor(int messageColor) {
        View view = toast.getView();
        if (view != null) {
            TextView message = view.findViewById(android.R.id.message);
            message.setTextColor(messageColor);
        }
        return this;
    }

    /**
     * 设置字的背景色
     *
     * @param messageColor 字的背景色值
     * @return
     */
    public ToastUtil setTextBackgroundColor(int messageColor) {
        View view = toast.getView();
        if (view != null) {
            TextView message = view.findViewById(android.R.id.message);
            message.setBackgroundColor(messageColor);
        }
        return this;
    }

    /**
     * 设置字的背景色
     *
     * @param drawable 字的背景
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ToastUtil setTextBackground(Drawable drawable) {
        View view = toast.getView();
        if (view != null) {
            TextView message = view.findViewById(android.R.id.message);
            message.setBackground(drawable);
        }
        return this;
    }

    /**
     * 设置字的背景色
     *
     * @param resid 字的背景
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ToastUtil setTextBackgroundResource(int resid) {
        View view = toast.getView();
        if (view != null) {
            TextView message = view.findViewById(android.R.id.message);
            message.setBackgroundResource(resid);
        }
        return this;
    }


    /**
     * 设置Toast背景
     *
     * @param drawable 背景
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ToastUtil setToastBackground(Drawable drawable) {
        View view = toast.getView();
        if (view != null) {
            view.setBackground(drawable);
        }
        return this;
    }

    /**
     * 设置Toast背景
     *
     * @param color 背景色
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ToastUtil setToastBackgroundColor(int color) {
        View view = toast.getView();
        if (view != null) {
            view.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * 设置Toast背景
     *
     * @param resid 背景
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ToastUtil setToastBackground(int resid) {
        View view = toast.getView();
        if (view != null) {
            view.setBackgroundResource(resid);
        }
        return this;
    }

    /**
     * 短时间显示Toast
     */
    public ToastUtil Short(Context context, CharSequence message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 短时间显示Toast
     */
    public ToastUtil Short(Context context, int message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 显示内容
     * @return
     */
    public ToastUtil Long(Context context, CharSequence message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return this;
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 显示内容
     */

    public ToastUtil Long(Context context, int message) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return this;
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param message  显示内容
     * @param duration 显示时间
     */
    public ToastUtil Indefinite(Context context, CharSequence message, int duration) {

        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, duration);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return this;
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param message  显示内容
     * @param duration 显示时间
     */
    public ToastUtil Indefinite(Context context, int message, int duration) {
        if (toast == null || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, duration);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return this;
    }

    /**
     * 显示Toast
     *
     * @return
     */
    public ToastUtil show() {
        toast.show();
        return this;
    }

    /**
     * 获取Toast
     *
     * @return
     */
    public Toast getToast() {
        return toast;
    }

}
