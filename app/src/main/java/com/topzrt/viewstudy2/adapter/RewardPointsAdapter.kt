package com.topzrt.viewstudy2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.bean.RewardPointDataListBean

/**
 * Created by Vincent;
 * Created on 2017/12/21;
 * DSC:
 */
class RewardPointsAdapter(var context: Context,
                          var data: List<RewardPointDataListBean>) : RecyclerView.Adapter<RewardPointsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Glide.with(context).load(data[position].img).into(holder?.iv)
        holder?.require?.text = data[position].score
        holder?.rule?.text = data[position].name
        holder?.btn_change?.setOnClickListener { Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reward_point, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val iv = view.findViewById<ImageView>(R.id.item_reward_point_iv)
        val require = view.findViewById<TextView>(R.id.item_reward_point_tv_require)
        val rule = view.findViewById<TextView>(R.id.item_reward_point_tv_rule)
        val btn_change = view.findViewById<TextView>(R.id.item_reward_point_btn_change)
    }

}