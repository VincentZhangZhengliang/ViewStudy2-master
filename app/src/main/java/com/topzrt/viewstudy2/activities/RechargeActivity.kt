package com.topzrt.viewstudy2.activities

import android.os.Build
import android.support.annotation.RequiresApi
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.R.id.activity_recharge_v
import kotlinx.android.synthetic.main.layout_toolbar.*

class RechargeActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_recharge
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(activity_recharge_v).init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP) override fun initView() {
        super.initView()
        toolbar.navigationIcon = getDrawable(R.drawable.ic_arrow_back_white_24dp)
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()

        toolbar.setNavigationOnClickListener {
            finish()
        }

    }


}
