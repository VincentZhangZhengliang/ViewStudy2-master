package com.topzrt.viewstudy2.ui.dealdetail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.topzrt.viewstudy2.R

/**
 * @author Python
 * @date 2018/3/25
 * @desc
 */
class DealDetailAdapter(var context: Context) : PagerAdapter() {


    val titles = listOf("借款信息", "风险控制", "投资人")

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {

        return view == `object`
    }

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var view: View?
        if (position == 0) {
            Log.e("Vincent", position.toString())
            view = LayoutInflater.from(context).inflate(R.layout.layout_deal_detail_loan_info, container, false)
        } else if (position == 1) {
            Log.e("Vincent", position.toString())
            view = LayoutInflater.from(context).inflate(R.layout.layout_deal_detail_risk_control, container, false)
        } else {
            Log.e("Vincent", position.toString())
            view = LayoutInflater.from(context).inflate(R.layout.layout_deal_detail_investors, container, false)
        }
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
//        super.destroyItem(container, position, `object`)
        container?.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}