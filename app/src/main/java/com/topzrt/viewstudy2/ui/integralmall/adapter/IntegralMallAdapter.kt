package com.topzrt.viewstudy2.ui.integralmall.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.ui.integralmall.IntegralMallActivity
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallListBean

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */
class IntegralMallAdapter(var context: Context, var data: List<IntegralMallListBean>) : BaseAdapter() {

    @SuppressLint("SetTextI18n") override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_integral_mall, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        Glide.with(context).load(data[position].img).into(holder.image)
        holder.score.text = data[position].score.toString()
        holder.btn.setOnClickListener {
            val integralMallActivity = context as IntegralMallActivity
            if (MyApplication.instance.mUser != null) {
                integralMallActivity.presenter.doChange(context, MyApplication.instance.mUser!!._Sessionkey, data[position].id, data[position].name, "topzrt", MyApplication.instance.mUser!!.mobile, "topzrt")
            }
        }
        holder.desc.text = data[position].name
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
        val image = view.findViewById<ImageView>(R.id.imageView4)!!
        val score = view.findViewById<TextView>(R.id.textView11)!!
        val btn = view.findViewById<Button>(R.id.button2)!!
        val desc = view.findViewById<TextView>(R.id.textView12)!!
    }

}