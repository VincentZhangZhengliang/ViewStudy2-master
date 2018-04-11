package com.topzrt.viewstudy2.ui.dealdetail

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat.OVER_SCROLL_NEVER
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.adapter.FinanceDetailAdapter
import com.topzrt.viewstudy2.adapter.VerticalPageTransformer
import com.topzrt.viewstudy2.base.BaseActivity
import kotlinx.android.synthetic.main.activity_finance_detail.*

@Suppress("DEPRECATION")
class FinanceDetailActivity : BaseActivity() {

    lateinit var dealType: String

    override fun getLayoutId(): Int {
        return R.layout.activity_finance_detail
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity_finance_detail_vvp.adapter = FinanceDetailAdapter(dealType.toInt(), supportFragmentManager)
        activity_finance_detail_vvp.overScrollMode = OVER_SCROLL_NEVER
        activity_finance_detail_vvp.setPageTransformer(true, VerticalPageTransformer())
    }

    override fun initData() {
        super.initData()
        dealType = intent.getStringExtra("deal_type")
    }


    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(activity_finance_detail_v)?.init()
    }
}
