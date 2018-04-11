package com.topzrt.viewstudy2.ui.finance_activity

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.TypedValue
import android.view.Gravity
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.ui.finance_activity.adapter.FinanceAdapter
import com.topzrt.viewstudy2.ui.finance_activity.bean.CatTypeListDataBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListDataBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListItemBean
import com.topzrt.viewstudy2.ui.finance_activity.presenter.FinancePresenter
import com.topzrt.viewstudy2.ui.finance_activity.view.IFinanceView
import kotlinx.android.synthetic.main.activity_finance.*

class FinanceActivity : BaseActivity(), IFinanceView {

    override fun onFresh(data: DealsListDataBean) {
        activity_finance_sv.onFinishFreshAndLoad()
        listData.clear()
        listData.addAll(data.item)
        adapter.notifyDataSetChanged()
    }

    override fun onLoadMore(data: DealsListDataBean) {
        activity_finance_sv.onFinishFreshAndLoad()
        listData.addAll(data.item)
        adapter.notifyDataSetChanged()
    }

    val presenter = FinancePresenter(this)
    val listData = ArrayList<DealsListItemBean>()
    var p: Int = 1
    var loan_type: Int = -1
    var adapter: FinanceAdapter = FinanceAdapter(this, listData)

    override fun toast(msg: String) {
        activity_finance_sv.onFinishFreshAndLoad()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_finance
    }

    @RequiresApi(Build.VERSION_CODES.M) override fun setCatType(data: CatTypeListDataBean) {
        data.loantype.forEachIndexed { i, loanTypeBean ->
            val cb = CheckBox(this)
            cb.buttonDrawable = null
            cb.text = loanTypeBean.loan_name
            if (i == 0) {
                cb.setTextColor(getColor(R.color.colorPrimary))
            } else {
                cb.setTextColor(Color.WHITE)
            }
            cb.setPadding(5, 5, 5, 5)
            cb.gravity = Gravity.CENTER
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            lp.setMargins(10, 0, 10, 0)
            cb.layoutParams = lp
            cb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            cb.setBackgroundResource(R.drawable.selector_finance_tag)
            cb.isChecked = i == 0
            cb.setOnCheckedChangeListener { _, isChecked ->
                clearCheckStatus()
                cb.isChecked = isChecked
                if (isChecked) {
                    doClick(data, i)
                    cb.setTextColor(getColor(R.color.colorPrimary))
                } else {
                    cb.setTextColor(getColor(R.color.white))
                }
            }
            activity_finance_ll_title_container.addView(cb)
        }
    }

    private fun doClick(data: CatTypeListDataBean, i: Int) {
        loan_type = data.loantype[i].loan_type
        presenter.dealsList(this, MyApplication.instance.mUser?._Sessionkey ?: "", 1, data.loantype[i].loan_type)
    }

    private fun clearCheckStatus() {
        val childCount = activity_finance_ll_title_container.childCount
        for (i in 0 until childCount) {
            val childAt = activity_finance_ll_title_container.getChildAt(i) as CheckBox
            childAt.isChecked = false
        }
    }

    override fun setDealsList(data: DealsListDataBean) {
        listData.clear()
        listData.addAll(data.item)
        p = data.page.page
        adapter.notifyDataSetChanged()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_finance_v).init()
    }

    override fun initView() {
        super.initView()
        activity_finance_lv.adapter = adapter
        activity_finance_sv.header = DefaultHeader(this)
        activity_finance_sv.footer = DefaultFooter(this)
    }

    override fun initData() {
        super.initData()
        presenter.getData(this, MyApplication.instance.mUser?._Sessionkey ?: "", 1, -1)
    }

    override fun initListener() {
        super.initListener()
        activity_finance_iv_back.setOnClickListener { finish() }
        activity_finance_sv.setListener(object : SpringView.OnFreshListener {
            override fun onLoadmore() {
                presenter.onLoadMore(this@FinanceActivity, MyApplication.instance.mUser?._Sessionkey ?: "", ++p, loan_type)
            }

            override fun onRefresh() {
                presenter.onFresh(this@FinanceActivity, MyApplication.instance.mUser?._Sessionkey ?: "", 1, loan_type)
            }
        })
    }

}
