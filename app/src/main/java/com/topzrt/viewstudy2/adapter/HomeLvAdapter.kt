package com.topzrt.viewstudy2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.bean.RecommendationDealBean
import com.topzrt.viewstudy2.view.CircleProgressView
import java.math.BigDecimal


/**
 * Created by Vincent;
 * Created on 2017/12/18;
 * DSC:
 */
class HomeLvAdapter(var context: Context, var list: List<RecommendationDealBean>) : BaseAdapter() {

    @SuppressLint("SetTextI18n") override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val holder: ViewHolder
        val view: View
        if (p1 == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_lv, p2, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = p1
            holder = view.tag as ViewHolder
        }
        holder.title.text = list[p0].name
        holder.rate.text = list[p0].rate
        holder.amount.text = "总额：" + list[p0].borrow_amount_format
        holder.dealTerm.text = list[p0].repay_time
        if ("0" == list[p0].repay_time_type) {
            holder.termUnit.text = "天"
        } else {
            holder.termUnit.text = "个月"
        }
        holder.left.text = "可投：" + list[p0].need_money

        val d = BigDecimal.valueOf(list[p0].load_money.toDouble()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal(list[p0].borrow_amount), 3, BigDecimal.ROUND_HALF_UP)
        when {
            d.toDouble() == 0.0 -> holder.progress.setProgress(d.toDouble())
            d.toDouble() > 0.1  -> holder.progress.setProgress(d.toDouble())
            else                -> holder.progress.setProgress(0.1)
        }
        return view
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    class ViewHolder(var view: View) {
        var title: TextView = view.findViewById(R.id.id_title)
        var rate: TextView = view.findViewById(R.id.id_rate)
        var amount: TextView = view.findViewById(R.id.id_amount)
        var left: TextView = view.findViewById(R.id.id_left)
        var dealTerm: TextView = view.findViewById(R.id.id_deal_term)
        var termUnit: TextView = view.findViewById(R.id.id_term_unit)
        var progress: CircleProgressView = view.findViewById(R.id.id_circle_progress)
    }
}

