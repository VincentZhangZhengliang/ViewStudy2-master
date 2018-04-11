package com.topzrt.viewstudy2.ui.dealdetail.fragment


import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.ui.dealdetail.DealDetailAdapter
import kotlinx.android.synthetic.main.fragment_deal_detail.*
import kotlinx.android.synthetic.main.layout_deal_detail_investors.*
import kotlinx.android.synthetic.main.layout_deal_detail_loan_info.*
import kotlinx.android.synthetic.main.layout_deal_detail_risk_control.*


/**
 * A simple [Fragment] subclass.
 */
class DealDetailFragment : BaseFragment() {

    override fun initView() {
        fragment_deal_detail_tablayout.addTab(fragment_deal_detail_tablayout.newTab().setText("借款信息"))
        fragment_deal_detail_tablayout.addTab(fragment_deal_detail_tablayout.newTab().setText("风险控制"))
        fragment_deal_detail_tablayout.addTab(fragment_deal_detail_tablayout.newTab().setText("投资人"))
    }

    override fun initListener() {
        fragment_deal_detail_tablayout.addOnTabSelectedListener(onTabSelectedListener)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_deal_detail
    }

    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {

            when (tab?.position) {
                0 -> {
                    layout_deal_detail_loan_info.visibility = View.VISIBLE
                    layout_deal_detail_risk_control.visibility = View.GONE
                    layout_deal_detail_investors.visibility = View.GONE
                }
                1 -> {
                    layout_deal_detail_loan_info.visibility = View.GONE
                    layout_deal_detail_risk_control.visibility = View.VISIBLE
                    layout_deal_detail_investors.visibility = View.GONE
                }
                2 -> {
                    layout_deal_detail_loan_info.visibility = View.GONE
                    layout_deal_detail_risk_control.visibility = View.GONE
                    layout_deal_detail_investors.visibility = View.VISIBLE
                }
            }

        }

    }

}
