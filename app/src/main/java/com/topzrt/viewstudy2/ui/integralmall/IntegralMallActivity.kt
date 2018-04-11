package com.topzrt.viewstudy2.ui.integralmall

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.ui.dochange.DoChangeRecordActivity
import com.topzrt.viewstudy2.ui.integralmall.adapter.IntegralMallAdapter
import com.topzrt.viewstudy2.ui.integralmall.bean.DoChangeBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallListBean
import com.topzrt.viewstudy2.ui.integralmall.presenter.IntegralMallPresenter
import com.topzrt.viewstudy2.ui.integralmall.view.IIntegralMallView
import com.topzrt.viewstudy2.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_integral_mall.*
import kotlinx.android.synthetic.main.header_integral_mall.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 积分商城
 */

class IntegralMallActivity : BaseActivity(), IIntegralMallView {

    val presenter = IntegralMallPresenter(this)

    private val datalist = ArrayList<IntegralMallListBean>()

    private val adapter = IntegralMallAdapter(this@IntegralMallActivity, datalist)

    override fun refreshData(integralMallBean: IntegralMallBean) {
        activity_integral_mall_springview.onFinishFreshAndLoad()
        datalist.clear()
        datalist.addAll(integralMallBean.data.list)
        adapter.notifyDataSetChanged()
        if (MyApplication.instance.mUser != null) {
            activity_integral_mall_tv_score.text = integralMallBean.data.scoreTotal
        }
    }

    override fun setData(integralMallBean: IntegralMallBean) {
        datalist.clear()
        if (integralMallBean?.data != null) {
            datalist.addAll(integralMallBean.data.list)
        }
        activity_integral_mall_lv.adapter = IntegralMallAdapter(this@IntegralMallActivity, datalist)
        if (MyApplication.instance.mUser != null) {
            activity_integral_mall_tv_score.text = integralMallBean.data.scoreTotal
        }
    }

    /**
     * 积分兑换后
     */
    override fun doChange(doChangeBean: DoChangeBean) {
        if (doChangeBean.status == 0) {
            Toast.makeText(this@IntegralMallActivity, doChangeBean.msg, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@IntegralMallActivity, "${doChangeBean.msg},详情在\"我的红包\"查看", Toast.LENGTH_SHORT).show()
            presenter.scoreRedbag(this, MyApplication.instance.mUser!!._Sessionkey)
        }
    }


    override fun initView() {
        super.initView()
        toolbar_right.text = "兑换须知"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_title.text = "积分商城"
        val headerView = LayoutInflater.from(this).inflate(R.layout.header_integral_mall, null)
        activity_integral_mall_lv.addHeaderView(headerView)

        if (MyApplication.instance.mUser == null) {
            activity_integral_mall_tv_score_tip.setTextColor(resources.getColor(R.color.cf6a633))
            activity_integral_mall_tv_score_tip.text = "登录查看积分"
        } else {
            activity_integral_mall_tv_score_tip.setTextColor(resources.getColor(R.color.c96C4FA))
            activity_integral_mall_tv_score_tip.text = "我的积分"
        }
        activity_integral_mall_springview.header = DefaultHeader(this@IntegralMallActivity)
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
        activity_integral_mall_springview.setListener(object : SpringView.OnFreshListener {
            override fun onLoadmore() {
            }

            override fun onRefresh() {
                presenter.onRefresh(this@IntegralMallActivity, MyApplication.instance.mUser?._Sessionkey
                        ?: "")

            }
        })

        activity_integral_mall_ll_do_change.setOnClickListener { startActivity(Intent(this, DoChangeRecordActivity::class.java)) }
        activity_integral_mall_ll_sign_up.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
    }

    override fun initData() {
        super.initData()
        presenter.scoreRedbag(this@IntegralMallActivity, MyApplication.instance.mUser?._Sessionkey
                ?: "")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_integral_mall
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_integral_mall_v).init()
    }


}
