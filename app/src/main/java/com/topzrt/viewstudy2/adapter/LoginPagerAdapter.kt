package com.topzrt.viewstudy2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.topzrt.viewstudy2.fragment.LoginFragment
import com.topzrt.viewstudy2.fragment.RegisterFragment

/**
 * Created by Vincent;
 * Created on 2017/12/29;
 * DSC:
 */
class LoginPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0    -> LoginFragment()
            1    -> RegisterFragment()
            else -> LoginFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}
