package com.topzrt.viewstudy2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterRepayListBean

/**
 * Created by Vincent;
 * Created on 2018/1/12;
 * DSC:  即将回款的adapter
 */
class SoonRepayAdapter(var context: Context, var data: MutableList<UcCenterRepayListBean>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_soon_repay, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.project.text = data[position].name
        holder.time.text = data[position].repay_date
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

    class ViewHolder(itemView: View) {
        val project = itemView.findViewById<TextView>(R.id.item_soon_repay_tv_project)
        val time = itemView.findViewById<TextView>(R.id.item_soon_repay_tv_time)
        val account = itemView.findViewById<TextView>(R.id.item_soon_repay_tv_account)
    }

}