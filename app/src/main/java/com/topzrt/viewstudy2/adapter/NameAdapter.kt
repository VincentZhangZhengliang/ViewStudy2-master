package com.topzrt.viewstudy2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.topzrt.viewstudy2.R

/**
 * Created by Vincent;
 * Created on 2017/11/27;
 * DSC:
 */
class NameAdapter(var context: Context, var data: List<String>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val holder: MyViewHolder
        val v: View
        if (p1 == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_name, p2, false)
            holder = MyViewHolder()
            holder.textview = v.findViewById(R.id.textView)
            v.tag = holder
        } else {
            v = p1
            holder = v.tag as MyViewHolder
        }
        holder.textview.text = data[p0]
        return v
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }


    class MyViewHolder {
        lateinit var textview: TextView
    }

}