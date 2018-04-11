package com.topzrt.viewstudy2

import android.content.Intent
import android.os.Build
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.utils.SpUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    val handler: Handler = Handler()
    val mlist: List<Int> = listOf(R.drawable.loading1, R.drawable.loading2, R.drawable.loading3, R.drawable.loading4)

    override fun getLayoutId(): Int {

        return R.layout.activity_splash
    }

    override fun initData() {
        val isfirst = SpUtils.getBoolean(this@SplashActivity, "isfirst", true, "sp")
        if (isfirst) {
            activity_splash_vp.adapter = pagerAdapter
            activity_splash_vp.addOnPageChangeListener(onPageChangeListener)
            initDot(0)
        } else {
            if (hasPermission(arrayOf(android.Manifest.permission.READ_PHONE_STATE))) {
                getQidongyeData()
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_PHONE_STATE), 100)
                }
            }
        }
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar!!.statusBarView(activity_splash_top_view).fullScreen(true).statusBarDarkFont(true, 0.2f).init()
    }

    override fun initListener() {
        super.initListener()
        activity_splash_btn.setOnClickListener {
            turnToActivity(this@SplashActivity, MainActivity::class.java, null)
            SpUtils.saveBoolean(this@SplashActivity, "isfirst", false, "sp")
            finish()
        }
    }

    /**
     * 初始化dot
     */
    private fun initDot(position: Int) {
        activity_splash_container.removeAllViews()
        for (i in 0 until mlist.size) {
            val view = View(this@SplashActivity)
            var layoutParams = LinearLayout.LayoutParams(20, 20)
            layoutParams.leftMargin = 10
            view.layoutParams = layoutParams
            if (i == position) view.setBackgroundResource(R.drawable.shape_btn_selected) else view.setBackgroundResource(R.drawable.shape_btn_normal)
            activity_splash_container.addView(view)
        }
    }

    /** viewpagerde adapter */
    private val pagerAdapter = object : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return mlist.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val image = ImageView(this@SplashActivity)
            image.scaleType = ImageView.ScaleType.FIT_XY
            image.setImageResource(mlist[position])
            container?.addView(image)
            return image
        }

//        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
//            super.destroyItem(container, position, `object`)
//            container?.removeView(`object` as View)
//        }


    }


    /**  viewpager的listener*/
    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float,
                                    positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            initDot(position)
            when (position) {
                0, 1, 2 -> {
                    activity_splash_btn.visibility = View.GONE
                }
                3       -> {
                    activity_splash_btn.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }


    override fun onPermissionResult() {
        super.onPermissionResult()
        getQidongyeData()
    }


    private fun getQidongyeData() {
        ReApiImpl.qiDongYe(this@SplashActivity,"").subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).map { t -> t.data.qdy_url }.observeOn(AndroidSchedulers.mainThread()).subscribe { t ->
            imageView2.visibility = View.VISIBLE
            Glide.with(this@SplashActivity).applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.defaultimg1)).load(t?.img).into(imageView2)
            handler.postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, 1000)
        }
    }

}

