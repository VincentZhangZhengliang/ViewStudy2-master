package com.topzrt.viewstudy2.ui.BbinIncome

import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.ui.BbinIncome.adapter.BbinIncomeAdapter
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeDataBean
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeListBean
import com.topzrt.viewstudy2.ui.BbinIncome.presenter.BbinIncomePresenter
import com.topzrt.viewstudy2.ui.BbinIncome.view.IBbinIncomeView
import kotlinx.android.synthetic.main.activity_bbin_income.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 体验金收益
 */
class BbinIncomeActivity : BaseActivity(), IBbinIncomeView {

    val presenter = BbinIncomePresenter(this)
    val listData = ArrayList<BbinIncomeListBean>()
    val adapter = BbinIncomeAdapter(this, listData)

    override fun setData(bbinIncomeDataBean: BbinIncomeDataBean) {
        listData.clear()
        listData.addAll(bbinIncomeDataBean.load_list)
        adapter.notifyDataSetChanged()
        activity_bbin_income_lv.adapter = adapter
    }

    override fun onRefresh(bbinIncomeDataBean: BbinIncomeDataBean) {
        listData.clear()
        listData.addAll(bbinIncomeDataBean.load_list)
        adapter.notifyDataSetChanged()
        activity_bbin_income_sv.onFinishFreshAndLoad()
    }

    override fun onLoadMore(bbinIncomeDataBean: BbinIncomeDataBean) {
        listData.clear()
        listData.addAll(bbinIncomeDataBean.load_list)
        adapter.notifyDataSetChanged()
        activity_bbin_income_sv.onFinishFreshAndLoad()
    }

    override fun toast(msg: String) {
        activity_bbin_income_sv.onFinishFreshAndLoad()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_bbin_income
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_bbin_income_v).init()
    }

    override fun initView() {
        super.initView()
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_title.text = "体验金收益"
        toolbar_right.text = ""
        activity_bbin_income_sv.header = DefaultHeader(this)
        activity_bbin_income_sv.footer = DefaultFooter(this)
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
        activity_bbin_income_sv.setListener(object : SpringView.OnFreshListener {
            override fun onLoadmore() {
                if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
                    presenter.onLoadMore(this@BbinIncomeActivity, MyApplication.instance.mUser!!._Sessionkey)
                } else {
                    toast("您还未登录~~~")
                }
            }

            override fun onRefresh() {
                if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
                    presenter.onRefresh(this@BbinIncomeActivity, MyApplication.instance.mUser!!._Sessionkey)
                } else {
                    toast("您还未登录~~~")
                }
            }
        })

    }

    override fun initData() {
        super.initData()
        if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
            presenter.showNewDeal(this@BbinIncomeActivity, MyApplication.instance.mUser!!._Sessionkey)
        } else {
            toast("您还未登录~~~")
        }
    }
}
