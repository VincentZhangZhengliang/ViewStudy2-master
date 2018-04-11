package com.topzrt.viewstudy2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.topzrt.viewstudy2.ui.dealdetail.fragment.DealDetailFragment
import com.topzrt.viewstudy2.ui.dealdetail.fragment.FinanceDetailFragment
import com.topzrt.viewstudy2.ui.dealdetail.fragment.TiYanFragment

/**
 * Created by Vincent;
 * Created on 2017/12/27;
 * DSC:   flag 标的类型 1新手体验表 0常规标 2专享标
 */
class FinanceDetailAdapter(var flag: Int, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                if (flag == 0) FinanceDetailFragment()
                else TiYanFragment()
            }
            1 -> DealDetailFragment()
            else -> FinanceDetailFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}