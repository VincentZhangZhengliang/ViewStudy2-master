package com.topzrt.viewstudy2.ui.dochange.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordListBean
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Vincent;
 * Created on 2018/1/24;
 * DSC:
 */
class DoChangeRecordAdapter(var context: Context, var data: List<DoChangeRecordListBean>) : BaseAdapter() {

    @SuppressLint("SimpleDateFormat")
    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    init {
        sdf.timeZone = TimeZone.getTimeZone("GMT+8:00")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_do_change_record, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.time.text = sdf.format( Date(data[position].ex_time.toLong()*1000))
        holder.title.text = data[position].goods_name
        holder.score.text = data[position].score
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
        val time = view.findViewById<TextView>(R.id.textView13)
        val title = view.findViewById<TextView>(R.id.textView14)
        val score = view.findViewById<TextView>(R.id.textView15)
    }

}