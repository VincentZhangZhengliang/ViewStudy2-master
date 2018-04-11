package com.topzrt.viewstudy2.ui.BbinIncome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeListBean

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
class BbinIncomeAdapter(var context: Context, var data: List<BbinIncomeListBean>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_bbin_income, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.tv_id.text = (position + 1).toString()
        holder.tv_name.text = data[position].name
        holder.tv_money.text = data[position].money
        holder.tv_create_time.text = data[position].create_date
        holder.tv_status.text = when (data[position].deal_status) {
        //0待等材料，1马上投标，2满标，3流标，4还款中，5已还清
            0    -> "待等材料"
            1    -> "马上投标"
            2    -> "满标"
            3    -> "流标"
            4    -> "还款中"
            5    -> "已还清"
            else -> "未知"
        }
        holder.tv_plan_time.text = data[position].yiji_time
        holder.tv_actual_time.text = data[position].update_date
        holder.tv_plan_earnings.text = data[position].interest_money
        holder.tv_actual_earnings.text = data[position].true_interest_money
        holder.tv_earnings_stats.text = when (data[position].has_pay) {
            0    -> "未转入金额"
            1    -> "已转入金额"
            else -> "计算中"
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
        val tv_id = view.findViewById<TextView>(R.id.item_bbin_income_tv_id)
        val tv_name = view.findViewById<TextView>(R.id.item_bbin_income_tv_name)
        val tv_money = view.findViewById<TextView>(R.id.item_bbin_income_tv_money)
        val tv_create_time = view.findViewById<TextView>(R.id.item_bbin_income_tv_create_time)
        val tv_status = view.findViewById<TextView>(R.id.item_bbin_income_tv_status)
        val tv_plan_time = view.findViewById<TextView>(R.id.item_bbin_income_tv_plan_time)
        val tv_actual_time = view.findViewById<TextView>(R.id.item_bbin_income_tv_actual_time)
        val tv_plan_earnings = view.findViewById<TextView>(R.id.item_bbin_income_tv_plan_earnings)
        val tv_actual_earnings = view.findViewById<TextView>(R.id.item_bbin_income_tv_actual_earnings)
        val tv_earnings_stats = view.findViewById<TextView>(R.id.item_bbin_income_tv_earnings_stats)
    }

}

