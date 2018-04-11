package com.topzrt.viewstudy2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.topzrt.viewstudy2.fragment.MsgFragment
import com.topzrt.viewstudy2.fragment.NoticeFragment

/**
 * Created by Vincent;
 * Created on 2017/12/20;
 * DSC:
 */

class MsgCenterPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    val titles = arrayListOf<String>("公告", "消息")

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0    -> NoticeFragment()
            1    -> MsgFragment()
            else -> NoticeFragment()
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {





        return titles[position]
    }
}