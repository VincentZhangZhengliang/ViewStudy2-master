package com.topzrt.viewstudy2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.topzrt.viewstudy2.R

/**
 * Created by Vincent;
 * Created on 2017/12/18;
 * DSC:
 */
class ActivityAdapter(var context: Context, var list: List<String>) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (p1 == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_activities, p2, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = p1
            holder = view.tag as ViewHolder
        }
        Glide.with(context).load(list[p0]).into(holder.pic)
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
        val pic = view.findViewById<ImageView>(R.id.item_activities_tv_pic)
    }

}