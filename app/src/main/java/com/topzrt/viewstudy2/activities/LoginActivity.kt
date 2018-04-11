package com.topzrt.viewstudy2.activities

import android.support.v4.view.ViewPager
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.adapter.LoginPagerAdapter
import com.topzrt.viewstudy2.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    var screenWidth: Int? = null
    var screenHeight: Int? = null
    var defaultWidth: Float? = null

    override fun initListener() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener({
            finish()
        })

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float,
                                        positionOffsetPixels: Int) {
                val indicatorWidth = screenWidth?.div(2)
                iv_indicator.animate().translationX(defaultWidth!!.plus(indicatorWidth?.times(position + positionOffset)!!)).duration = 300
            }

            override fun onPageSelected(position: Int) {
            }
        })

        tv_login.setOnClickListener {
            viewpager.currentItem = 0
        }

        tv_register.setOnClickListener {
            viewpager.currentItem = 1
        }
    }

    override fun initData() {

    }

    override fun onPermissionResult() {
        //        viewpager.adapter = LoginAdapter(this@LoginActivity)
    }

    override fun initView() {
        screenWidth = getScreenSize().first                 //获取屏幕的宽高
        screenHeight = getScreenSize().second
        toolbar.setNavigationIcon(R.drawable.back_1_img)    //设置导航的图标
        toolbar.title = "denglu"                            //设置indicator的位置
        val layoutParams = iv_indicator.layoutParams
        defaultWidth = (screenWidth!! / 4).toFloat() - layoutParams.width / 2
//        viewpager.adapter = LoginAdapter(this@LoginActivity, this)
        viewpager.adapter = LoginPagerAdapter(supportFragmentManager)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar!!.statusBarView(top_view).navigationBarColor(
                R.color.colorPrimary).fullScreen(true).statusBarDarkFont(true, 0.2f).init()
    }

}
