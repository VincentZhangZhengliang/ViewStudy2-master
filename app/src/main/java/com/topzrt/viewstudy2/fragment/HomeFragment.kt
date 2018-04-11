package com.topzrt.viewstudy2.fragment


import android.content.Intent
import android.support.v4.app.Fragment
import android.widget.AdapterView
import android.widget.Toast
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.activities.FreshmanActivity
import com.topzrt.viewstudy2.activities.MsgCenterActivity
import com.topzrt.viewstudy2.activities.RechargeActivity
import com.topzrt.viewstudy2.adapter.HomeLvAdapter
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.bean.HomeBannerBean
import com.topzrt.viewstudy2.bean.RecommendationBean
import com.topzrt.viewstudy2.bean.RecommendationDealBean
import com.topzrt.viewstudy2.event.HomeScrollEvent
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.dealdetail.FinanceDetailActivity
import com.topzrt.viewstudy2.ui.finance_activity.FinanceActivity
import com.topzrt.viewstudy2.ui.integralmall.IntegralMallActivity
import com.topzrt.viewstudy2.ui.safe_guarantee.SafeGuaranteeActivity
import com.topzrt.viewstudy2.utils.GlideImageLoader
import com.topzrt.viewstudy2.utils.L
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    val bannerList: MutableList<String> = mutableListOf()
    val dealList: MutableList<RecommendationDealBean> = mutableListOf()

    var refreshType: Int = 0       //0:第一次加载数据  1：刷新数据  2： 加载更多

    override fun initData() {
        if (refreshType == 0) {

        }
        Observable.zip(ReApiImpl.licaiRecommendation(activity, "").subscribeOn(Schedulers.io()), ReApiImpl.banner(activity, "").subscribeOn(Schedulers.io()), BiFunction<RecommendationBean, HomeBannerBean, Pair<RecommendationBean, HomeBannerBean>> { t1, t2 ->
            Pair(t1, t2)
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Pair<RecommendationBean, HomeBannerBean>> {

            override fun onComplete() {
                when (refreshType) {
                    1 -> {
                        fragment_home_sv.onFinishFreshAndLoad()
                        Toast.makeText(activity, "刷新成功", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        fragment_home_sv.onFinishFreshAndLoad()
                        Toast.makeText(activity, "加载成功", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onError(e: Throwable) {}

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: Pair<RecommendationBean, HomeBannerBean>) {
                val homeBannerBean = t.second
                bannerList.clear()
                homeBannerBean.data.forEach {
                    bannerList.add(it.img)
                }
                fragment_home_banner.setImageLoader(GlideImageLoader())
                fragment_home_banner.setImages(bannerList)
                fragment_home_banner.start()
                val recommendationBean = t.first
                dealList.clear()
                dealList.addAll(recommendationBean.data.deal_list)
                fragment_home_slv.adapter = HomeLvAdapter(activity, dealList)
                fragment_home_scrollview.smoothScrollTo(0, 0)
            }
        })

    }

    override fun initView() {

    }

    override fun initListener() {
        fragment_home_sv.header = DefaultHeader(activity)
        fragment_home_sv.footer = DefaultFooter(activity)
        fragment_home_sv.setListener(object : SpringView.OnFreshListener {
            override fun onLoadmore() {
                refreshType = 2
                initData()
            }

            override fun onRefresh() {
                refreshType = 1
                initData()
            }
        })

        fragment_home_slv.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, position, id ->
            val intent = Intent(activity, FinanceDetailActivity::class.java)
            intent.putExtra("id", dealList[position].id)                          //标的ID
                    .putExtra("cg_type", dealList[position].cg_type)              //标的类型：2(华兴存管),1(丰付)
                    .putExtra("borrow_amount", dealList[position].borrow_amount)  //标的总额
                    .putExtra("deal_type", dealList.get(position).deal_type)      //标的类型：0常规标  1体验标  2新手标
            startActivity(intent)
        }
        //充值
        fragment_home_ll_recharge.setOnClickListener {
            startActivity(Intent(activity, RechargeActivity::class.java))
        }
        //积分商城
        fragment_home_ll_shop.setOnClickListener {
            startActivity(Intent(activity, IntegralMallActivity::class.java))
        }
        //新手专区
        fragment_home_rl_freshman.setOnClickListener {
            startActivity(Intent(activity, FreshmanActivity::class.java))
        }
        //消息中心
        fragment_home_ll_msg.setOnClickListener {
            startActivity(Intent(activity, MsgCenterActivity::class.java))
        }
        //安全保障
        fragment_home_ll_safe.setOnClickListener {
            //            val intent = Intent(activity, MyWebActivity::class.java)
//            intent.putExtra("title", "安全保障")
//            intent.putExtra("hurl", "")
//            intent.putExtra("where", "")
//            startActivity(intent)
            startActivity(Intent(activity, SafeGuaranteeActivity::class.java))
        }

        fragment_home_tv_check_more.setOnClickListener {
            startActivity(Intent(activity, FinanceActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onStart() {
        super.onStart()
        fragment_home_scrollview.smoothScrollTo(0, 0)
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 处理homefragment滑动到顶部
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false)
    fun onHomeScroolEvent(homeScrollEvent: HomeScrollEvent) {
        if (homeScrollEvent.flag) {
            fragment_home_scrollview.smoothScrollTo(0, 0)
        }
    }


}

