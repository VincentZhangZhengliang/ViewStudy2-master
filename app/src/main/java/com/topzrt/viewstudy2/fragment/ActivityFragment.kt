package com.topzrt.viewstudy2.fragment


import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.adapter.ActivityAdapter
import com.topzrt.viewstudy2.network.ReApiImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_activity.*
import kotlinx.android.synthetic.main.layout_toolbar.*


/**
 * A simple [Fragment] subclass.
 */
class ActivityFragment : BaseFragment() {

    private val bannerList: MutableList<String> = mutableListOf()

    override fun initData() {
        toolbar_title.text = "活动专区"
        toolbar_right.visibility = View.GONE
        ReApiImpl.banner(activity, "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                it.data.forEach {
                    bannerList.add(it.img)
                }
                fragment_activity_lv.adapter = ActivityAdapter(activity, bannerList)
            } else {
                Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initView() {
        ImmersionBar.with(this).statusBarView(fragment_activity_v).init()

    }

    override fun initListener() {
        fragment_activity_sv.header = DefaultHeader(activity)
        fragment_activity_sv.footer = DefaultFooter(activity)
        fragment_activity_sv.setListener(object : SpringView.OnFreshListener {
            override fun onLoadmore() {
                fragment_activity_sv.onFinishFreshAndLoad()
            }

            override fun onRefresh() {
                fragment_activity_sv.onFinishFreshAndLoad()
            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.fragment_activity
    }


}
