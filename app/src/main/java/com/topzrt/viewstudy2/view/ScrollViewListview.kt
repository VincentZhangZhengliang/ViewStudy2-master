package com.topzrt.viewstudy2.view

import android.content.Context
import android.view.View
import android.widget.ListView

/**
 * Created by Vincent;
 * Created on 2017/12/18;
 * DSC:嵌套在scrollview中使用的listview
 */
class ScrollViewListview(context: Context) : ListView(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }

}