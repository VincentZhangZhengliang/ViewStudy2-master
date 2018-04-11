package com.topzrt.viewstudy2.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.*
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.bean.H5Bean
import com.topzrt.viewstudy2.utils.Api
import com.topzrt.viewstudy2.view.LoadingDialog
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_my_web.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import okhttp3.Call
import java.lang.Exception


class MyWebActivity : BaseActivity() {

    lateinit var title: String
    lateinit var hurl: String
    var where: String = ""     //标记从哪个页面跳转过来

    override fun getLayoutId(): Int {
        return R.layout.activity_my_web
    }

    override fun initData() {
        super.initData()
        title = intent.getStringExtra("title")
        hurl = intent.getStringExtra("hurl")
        where = intent.getStringExtra("where")

        when (where) {

            "SafeGuarantee" -> {
                Api.getH5("2", object : StringCallback() {
                    override fun onResponse(response: String?, id: Int) {
                        Log.e("Vincent", response)
                        val gson = Gson()
                        val h5Bean = gson.fromJson<H5Bean>(response, H5Bean::class.java)
                        loadData(h5Bean.data.url)
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                    }
                })
            }
            else            -> loadData(hurl)
        }

    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface") override fun initView() {
        super.initView()
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    }

    @SuppressLint("JavascriptInterface") private fun loadData(hurl: String) {
        val settings = activity_myweb_wv.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.blockNetworkImage = false
        settings.pluginState = WebSettings.PluginState.ON
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        activity_myweb_wv.addJavascriptInterface(JavaScriptInterface(), "topzrt")
        activity_myweb_wv.webViewClient = MyWebViewClient()
        activity_myweb_wv.webChromeClient = MyWebChromeClient()
        activity_myweb_wv.setDownloadListener(MyWebViewDownLoadListener())
        activity_myweb_wv.loadUrl(hurl)
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_myweb_v).init()
    }

    private inner class MyWebChromeClient : WebChromeClient() {

        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null

        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress > 90) {
                toolbar_title.text = title
            }
        }

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            super.onShowCustomView(view, callback)
            if (mCustomView != null) {
                callback.onCustomViewHidden()
                return
            }
            mCustomView = view
            activity_myweb_fl.addView(mCustomView)
            mCustomViewCallback = callback
            activity_myweb_wv.visibility = View.GONE
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        override fun onHideCustomView() {
            if (mCustomView == null) {
                return
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            activity_myweb_wv.visibility = View.VISIBLE
            mCustomView!!.visibility = View.GONE
            activity_myweb_fl.removeView(mCustomView)
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomView = null
            super.onHideCustomView()
        }
    }

    private inner class MyWebViewClient : WebViewClient() {

        var loadingDialog: LoadingDialog = LoadingDialog(this@MyWebActivity)

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            //            loadingDialog.stopAnimator()
            loadingDialog.dismiss()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            loadingDialog.show()
        }

    }

    private inner class MyWebViewDownLoadListener : DownloadListener {

        override fun onDownloadStart(url: String, userAgent: String, contentDisposition: String,
                                     mimetype: String, contentLength: Long) {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    inner class JavaScriptInterface {
        //
        //        @JavascriptInterface fun toLogin() {
        //            Log.e("h5", "登陆")
        //            startActivityForResult(Intent(this@MyWebActivity, LoginActivity::class.java), Constants.HTML_LOGIN)
        //        }
        //
        //        @JavascriptInterface fun toLicai() {
        //            Log.e("h5", "理财页")
        //            val intent = Intent()
        //            intent.setClass(this@MyWebActivity, FinanceActivity::class.java!!)
        //            startActivity(intent)
        //        }
        //
        //        @JavascriptInterface fun toChongzhi() {
        //            Log.e("h5", "充值页")
        //            startActivityForResult(Intent(this@MyWebActivity, RechargeActivity::class.java), Constants.HTML_CHONGZHI)
        //        }
        //
        //        @JavascriptInterface fun goRealName() {
        //            Log.e("h5", "实名认证页")
        //            val intent = Intent(this@MyWebActivity, IDCardCheckActivity::class.java)
        //            startActivity(intent)
        //        }
        //
        //        @JavascriptInterface fun toTixian() {
        //            Log.e("h5", "提现")
        //            startActivityForResult(Intent(this@MyWebActivity, WithdrawActivity::class.java), Constants.HTML_TIXIAN)
        //        }
        //
        //        @JavascriptInterface fun toHongbao() {
        //            Log.e("h5", "红包")
        //            startActivityForResult(Intent(this@MyWebActivity, MyRedPackageActivity::class.java), Constants.HTML_HONGBAO)
        //        }
        //
        //        @JavascriptInterface fun toZhuce() {
        //            Log.e("h5", "注册")
        //            startActivityForResult(Intent(this@MyWebActivity, LoginAndRegisterActivity::class.java).putExtra("intent_type", 2), Constants.HTML_HONGBAO)
        //        }
        //
        //        @JavascriptInterface fun toFriend() {
        //            Log.e("h5", "邀请好友")
        //            startActivityForResult(Intent(this@MyWebActivity, MyTuijianma::class.java), Constants.HTML_HONGBAO)
        //        }
        //
        //        @JavascriptInterface fun toMyInvite() {
        //            Log.e("h5", "我的推荐")
        //            startActivityForResult(Intent(this@MyWebActivity, MyTuijianma::class.java), Constants.HTML_HONGBAO)
        //        }
        //
        //        @JavascriptInterface fun toMain() {
        //            Log.e("h5", "首页")
        //            val intent = Intent(this@MyWebActivity, MainActivity::class.java)
        //            startActivity(intent)
        //        }
        //
        //        @JavascriptInterface fun toLicaiManager() {
        //            Log.e("h5", "理财经理")
        //            val saveFinaceManager = MyAppUtil.getSaveFinaceManager(this@MyWebActivity, loginBean.getId() + "")
        //            if (saveFinaceManager == null || saveFinaceManager === "") {      //第一次进入理财经理页面
        //                startActivity(Intent(this@MyWebActivity, FinanceManagerHomeActivity::class.java))
        //            } else if (saveFinaceManager == "1") {
        //                startActivity(Intent(this@MyWebActivity, FinanceManagerDetailsActivity::class.java))    //理财经理详情页
        //            }
        //        }
    }

}
