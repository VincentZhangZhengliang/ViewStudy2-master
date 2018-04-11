package com.topzrt.viewstudy2.activities

import android.content.Intent
import android.support.v4.view.ViewPager
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.adapter.MsgCenterPageAdapter
import kotlinx.android.synthetic.main.activity_msg_center.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MsgCenterActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_msg_center
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_msg_center_v).init()
    }

    override fun initData() {
        super.initData()
        activity_msg_center_vp.adapter = MsgCenterPageAdapter(supportFragmentManager)
        activity_msg_center_tl.addTab(activity_msg_center_tl.newTab().setText("公告"))
        activity_msg_center_tl.addTab(activity_msg_center_tl.newTab().setText("消息"))
        activity_msg_center_tl.setupWithViewPager(activity_msg_center_vp, true)
    }

    override fun initView() {
        super.initView()
        toolbar_title.text = "消息中心"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_right.text = "标记为已读"
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
        toolbar_right.setOnClickListener {

        }

        activity_msg_center_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    1 -> {
                        if (MyApplication.instance.mUser == null) {
                            startActivity(Intent(this@MsgCenterActivity, LoginActivity::class.java))
                            activity_msg_center_vp.currentItem = 0
                        }
                    }
                }
            }
        })
    }


}
