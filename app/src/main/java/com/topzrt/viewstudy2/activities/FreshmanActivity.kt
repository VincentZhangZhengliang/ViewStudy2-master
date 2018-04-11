package com.topzrt.viewstudy2.activities

import android.content.Intent
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.bean.FreshmanBean
import com.topzrt.viewstudy2.bean.FreshmanDataBean
import com.topzrt.viewstudy2.ui.BbinIncome.BbinIncomeActivity
import com.topzrt.viewstudy2.utils.Api
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_freshman.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import okhttp3.Call
import java.lang.Exception


/**
 * 新手专区
 */
class FreshmanActivity : BaseActivity() {

    lateinit var freshmanBean: FreshmanBean
    lateinit var freshmanDataBean: FreshmanDataBean

    override fun getLayoutId(): Int {
        return R.layout.activity_freshman
    }

    override fun initData() {
        super.initData()

        Api.noviceZone(object : StringCallback() {

            override fun onResponse(response: String?, id: Int) {
                val gson = Gson()
                freshmanBean = gson.fromJson<FreshmanBean>(response, FreshmanBean::class.java)
                freshmanDataBean = freshmanBean.data
                Glide.with(this@FreshmanActivity).load(freshmanDataBean.list[0].img_src).into(activity_freshman_iv_1)
                Glide.with(this@FreshmanActivity).load(freshmanDataBean.list[1].img_src).into(activity_freshman_iv_2)
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {}
        })
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_freshman_v).init()
    }

    override fun initView() {
        super.initView()
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_title.text = "新手专区"
        toolbar_right.text = "体验金收益"
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener {
            finish()
        }

        activity_freshman_iv_1.setOnClickListener {
            val intent = Intent(this@FreshmanActivity, MyWebActivity::class.java)
            intent.putExtra("title", freshmanDataBean.list[0].title)
            intent.putExtra("hurl", freshmanDataBean.list[0].hurl)
            intent.putExtra("where", "Freshman")
            startActivity(intent)
        }

        activity_freshman_iv_2.setOnClickListener {
            val intent = Intent(this@FreshmanActivity, MyWebActivity::class.java)
            intent.putExtra("title", freshmanDataBean.list[1].title)
            intent.putExtra("hurl", freshmanDataBean.list[1].hurl)
            intent.putExtra("where", "Freshman")
            startActivity(intent)
        }
        toolbar_right.setOnClickListener { startActivity(Intent(this, BbinIncomeActivity::class.java)) }

    }

}
