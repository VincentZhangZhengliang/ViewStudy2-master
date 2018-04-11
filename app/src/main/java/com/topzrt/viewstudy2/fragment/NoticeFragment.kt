package com.topzrt.viewstudy2.fragment


import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.activities.NoticeDetailActivity
import com.topzrt.viewstudy2.adapter.NoticeAdapter
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.bean.NoticeBean
import com.topzrt.viewstudy2.bean.NoticeDataBean
import com.topzrt.viewstudy2.bean.NoticeListBean
import com.topzrt.viewstudy2.utils.Api
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.fragment_notice.*
import okhttp3.Call
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 */
class NoticeFragment : BaseFragment() {

    var data: MutableList<NoticeListBean> = mutableListOf()
    var p: String = "1"
    var refreshType = 0          //0:刷新 1:加载更多
    lateinit var noticeDataBean: NoticeDataBean

    lateinit var adapter: NoticeAdapter

    override fun initView() {

    }

    override fun initListener() {
        fragment_notice_sv.footer = DefaultFooter(activity)
        fragment_notice_sv.header = DefaultHeader(activity)
        fragment_notice_sv.setListener(object : SpringView.OnFreshListener {
            override fun onLoadmore() {
                refreshType = 1
                getData((noticeDataBean.page.page + 1).toString(), refreshType)
            }

            override fun onRefresh() {
                refreshType = 0
                getData("1", refreshType)
            }

        })
        adapter.setOnItemClickLitener(object : NoticeAdapter.OnItemClickLitener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(activity, NoticeDetailActivity::class.java)
                intent.putExtra("title", data[position].title)
                intent.putExtra("id", data[position].id)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(activity, "onItemLongClick " + position.toString(), Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun initData() {
        super.initData()
        adapter = NoticeAdapter(activity, data)
        fragment_notice_rv.adapter = adapter
        fragment_notice_rv.layoutManager = LinearLayoutManager(activity)
        getData(p, refreshType)
    }

    private fun getData(p: String, refreshType: Int) {
        Api.getNotice(p, object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                Log.e("Vincent", response)
                fragment_notice_sv.onFinishFreshAndLoad()
                val gson = Gson()
                val noticeBean = gson.fromJson<NoticeBean>(response, NoticeBean::class.java)
                noticeDataBean = noticeBean.data
                if (refreshType == 0) {
                    data.clear()
                }
                data.addAll(noticeBean.data.list)
                fragment_notice_rv.adapter.notifyDataSetChanged()
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                fragment_notice_sv.onFinishFreshAndLoad()
            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.fragment_notice
    }

}
