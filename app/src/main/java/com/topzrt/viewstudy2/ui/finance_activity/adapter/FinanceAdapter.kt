package com.topzrt.viewstudy2.ui.finance_activity.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListItemBean
import com.topzrt.viewstudy2.view.CircleProgressView
import java.math.BigDecimal

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */
class FinanceAdapter(var context: Context, var data: List<DealsListItemBean>) : BaseAdapter() {

    @SuppressLint("SetTextI18n") override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_lv, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.title.text = data[position].name
        holder.rate.text = data[position].rate.toString()
        holder.amount.text = "总额：${data[position].borrow_amount_format}"
        holder.dealTerm.text = data[position].repay_time.toString()
        if (0 == data[position].repay_time_type) {
            holder.termUnit.text = "天"
        } else {
            holder.termUnit.text = "个月"
        }
        holder.left.text = "可投：" + data[position].need_money
        val d = BigDecimal.valueOf(data[position].load_money.toDouble()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal(data[position].borrow_amount), 3, BigDecimal.ROUND_HALF_UP)
        when {
            d.toDouble() == 0.0 -> holder.progress.setProgress(d.toDouble())
            d.toDouble() > 0.1  -> holder.progress.setProgress(d.toDouble())
            else                -> holder.progress.setProgress(0.1)
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
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