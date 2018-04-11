package com.topzrt.viewstudy2


import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.topzrt.viewstudy2.activities.LoginActivity
import com.topzrt.viewstudy2.adapter.HomePagerAdapter
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.event.HomeScrollEvent
import com.topzrt.viewstudy2.ui.account.fragment.AccountFragment
import com.topzrt.viewstudy2.fragment.ActivityFragment
import com.topzrt.viewstudy2.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity() {

    val list = listOf<Fragment>(HomeFragment(), ActivityFragment(), AccountFragment())

    override fun initListener() {

        activity_main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                when (position) {
                    0 -> {
                        bottomNavigationView.menu.getItem(position).isChecked = true
                        EventBus.getDefault().post(HomeScrollEvent(true))
                    }
                    1 -> {
                        bottomNavigationView.menu.getItem(position).isChecked = true
                    }
                    2 -> {
                        if (MyApplication.instance.mUser != null) {
                            bottomNavigationView.menu.getItem(position).isChecked = true
                        } else {
                            activity_main_viewpager.currentItem = 1
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        }
                    }
                }
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var result = true
            when (item.itemId) {
                R.id.home -> {
                    activity_main_viewpager.currentItem = 0
                    EventBus.getDefault().post(HomeScrollEvent(true))
                    result = true
                }
                R.id.activity -> {
                    activity_main_viewpager.currentItem = 1
                    result = true
                }
                R.id.account -> {
                    if (MyApplication.instance.mUser != null) {
                        activity_main_viewpager.currentItem = 2
                        result = true
                    } else {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        result = false
                    }
                }
            }
            result
        }
    }

    override fun initData() {
        val adapter = HomePagerAdapter(list, supportFragmentManager)
        activity_main_viewpager.adapter = adapter
        activity_main_viewpager.offscreenPageLimit = 2
    }

    override fun initView() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.keyboardEnable(true)?.navigationBarColor(
                R.color.colorPrimary)?.navigationBarWithKitkatEnable(false)?.init()
    }

}
