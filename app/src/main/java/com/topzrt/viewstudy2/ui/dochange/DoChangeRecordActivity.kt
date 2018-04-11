package com.topzrt.viewstudy2.ui.dochange

import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.ui.dochange.adapter.DoChangeRecordAdapter
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordBean
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordListBean
import com.topzrt.viewstudy2.ui.dochange.presenter.DoChangeRecordPresenter
import com.topzrt.viewstudy2.ui.dochange.view.IDoChangeRecordView
import kotlinx.android.synthetic.main.activity_do_change_record.*
import kotlinx.android.synthetic.main.layout_toolbar.*


/**
 * 积分兑换记录
 */
class DoChangeRecordActivity : BaseActivity(), IDoChangeRecordView {

    var listData = ArrayList<DoChangeRecordListBean>()
    val adapter = DoChangeRecordAdapter(this@DoChangeRecordActivity, listData)
    val presenter = DoChangeRecordPresenter(this)
    private lateinit var doChangeRecordBean: DoChangeRecordBean

    override fun initData() {
        super.initData()
        if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
            presenter.recordChange(this@DoChangeRecordActivity, MyApplication.instance.mUser!!._Sessionkey, 1)
        }
    }

    override fun setData(data: DoChangeRecordBean) {
        doChangeRecordBean = data
        activity_dochange_lv.adapter = adapter
        listData.clear()
        listData.addAll(data.data.list)
        adapter.notifyDataSetChanged()
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
        activity_do_change_record_springview.setListener(object : SpringView.OnFreshListener {

            override fun onLoadmore() {
                if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
                    if (doChangeRecordBean.data.page.page < doChangeRecordBean.data.page.page_total) {
                        presenter.onLoadmore(this@DoChangeRecordActivity, MyApplication.instance.mUser!!._Sessionkey, doChangeRecordBean.data.page.page + 1)
                    } else {
                        toast("没有更多数据~~~")
                    }
                } else {
                    toast("请先登录~~~")
                }
            }

            override fun onRefresh() {
                if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
                    presenter.onRefresh(this@DoChangeRecordActivity, MyApplication.instance.mUser!!._Sessionkey, 1)
                } else {
                    toast("请先登录~~~")
                }
            }

        })
    }

    override fun onRefresh(data: DoChangeRecordBean) {
        activity_do_change_record_springview.onFinishFreshAndLoad()
        toast("刷新成功~~~")
        doChangeRecordBean = data
        listData.clear()
        listData.addAll(data.data.list)
        adapter.notifyDataSetChanged()
    }

    override fun onLoadmore(data: DoChangeRecordBean) {
        toast("加载成功~~~")
        activity_do_change_record_springview.onFinishFreshAndLoad()
        doChangeRecordBean = data
        listData.addAll(data.data.list)
        adapter.notifyDataSetChanged()
    }

    override fun toast(msg: String) {
        activity_do_change_record_springview.onFinishFreshAndLoad()
        Toast.makeText(this@DoChangeRecordActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun initView() {
        super.initView()
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_title.text = "积分兑换记录"
        toolbar_right.text = ""
        activity_do_change_record_springview.header = DefaultHeader(this)
        activity_do_change_record_springview.footer = DefaultFooter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_do_change_record
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_do_change_record_v).init()
    }

}
