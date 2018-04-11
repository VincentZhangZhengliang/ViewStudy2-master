package com.topzrt.viewstudy2

import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.bean.QiDongYeBean
import com.topzrt.viewstudy2.utils.Api
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_second.*
import okhttp3.Call
import java.lang.Exception


class SecondActivity : BaseActivity() {

    override fun initListener() {

    }

    override fun initData() {

        Api.qiDongye(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                Log.e("Vincent", response)
                val gson = Gson()
                val qiDongYeBean = gson.fromJson<QiDongYeBean>(response, QiDongYeBean::class.java)
                if (qiDongYeBean?.status == 1) {   //成功
                    Log.e("Vincent", qiDongYeBean.data.qdy_url.img)
                    val url = "https://img.topzrt.com/public/attachment/201710/24/11/59eeb08b78a80.jpg"
                    Glide.with(this@SecondActivity).load(url).into(imageView)
                } else {
                    Toast.makeText(this@SecondActivity, qiDongYeBean?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {

            }
        })

    }

    override fun initView() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_second
    }

}
