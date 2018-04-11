package com.topzrt.viewstudy2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.bean.NoticeListBean


/**
 * Created by Vincent;
 * Created on 2017/12/20;
 * DSC:
 */
class NoticeAdapter(var context: Context,
                    var data: List<NoticeListBean>) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    interface OnItemClickLitener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private var mOnItemClickLitener: OnItemClickLitener? = null

    fun setOnItemClickLitener(mOnItemClickLitener: OnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.title?.text = data[position].title
        holder?.date?.text = data[position].create_time

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder?.view?.setOnClickListener {
                val pos = holder.layoutPosition
                mOnItemClickLitener?.onItemClick(holder.view, pos)
            }

            holder?.view?.setOnLongClickListener {
                val pos = holder.layoutPosition
                mOnItemClickLitener?.onItemLongClick(holder.itemView, pos)
                false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.item_notice_tv_title)
        val date = view.findViewById<TextView>(R.id.item_notice_tv_date)
    }


}
