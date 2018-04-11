package com.topzrt.viewstudy2.ui.safe_guarantee

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.bean.H5DataBean
import com.topzrt.viewstudy2.ui.safe_guarantee.presenter.SafeGuaranteePresenter
import com.topzrt.viewstudy2.ui.safe_guarantee.view.ISafeguaranteeView
import com.topzrt.viewstudy2.utils.Api
import com.topzrt.viewstudy2.utils.L
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_safe_guarantee.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import okhttp3.Call
import java.lang.Exception

class SafeGuaranteeActivity : BaseActivity(), ISafeguaranteeView {

    val presenter = SafeGuaranteePresenter(this)

    override fun toast(msg: String) {
        Toast.makeText(this@SafeGuaranteeActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: H5DataBean) {
        activity_safe_guarantee_wv.loadUrl(data.url)
    }

    @SuppressLint("SetJavaScriptEnabled") override fun initView() {
        super.initView()

        toolbar_title.text = "安全保障"
        toolbar_right.text = ""
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)

        val settings = activity_safe_guarantee_wv.settings
        settings.javaScriptEnabled = true                                                           //设置支持Javascript
        settings.pluginState = WebSettings.PluginState.ON                                           //支持插件
        //设置自适应屏幕，两者合用
        settings.useWideViewPort = true                                                             //将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true                                                        // 缩放至屏幕的大小

        settings.setSupportZoom(true)                                                               //支持缩放，默认为true。是下面那个的前提。
        settings.builtInZoomControls = true                                                         //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.displayZoomControls = false                                                        //隐藏原生的缩放控件

        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK                                    //关闭webview中缓存
        settings.allowFileAccess = true                                                             //设置可以访问文件
        settings.javaScriptCanOpenWindowsAutomatically = true                                       //支持通过JS打开新窗口
        settings.loadsImagesAutomatically = true                                                    //支持自动加载图片
        settings.defaultTextEncodingName = "utf-8"                                                  //设置编码格式

        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        activity_safe_guarantee_wv.webViewClient = MyWebViewClient
        activity_safe_guarantee_wv.webChromeClient = MyWebChromeClient
    }

    override fun initData() {
        super.initData()
        presenter.get_h5(this, 2)

        Api.posthttps(object : StringCallback() {

            override fun onResponse(response: String?, id: Int) {
                L.e(" " + response)
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                L.e("" + e?.message)
            }
        })
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_safe_guarantee
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_safe_guarantee_v).init()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KEYCODE_BACK) && activity_safe_guarantee_wv.canGoBack()) {
            activity_safe_guarantee_wv.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private val MyWebViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            L.e("onPageFinished 页面加载结束" + url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            L.e("onPageStarted 开始载入页面" + url)
        }

        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url)
            L.e("onLoadResource" + url)
            //在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
        }

        @RequiresApi(Build.VERSION_CODES.M) override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            L.e("onReceivedError" + error?.errorCode)
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            L.e("onReceivedSslError" + error?.url)
        }
    }

    private val MyWebChromeClient = object : WebChromeClient() {

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            toolbar_title.text = title
        }


        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            L.e("onProgressChanged $newProgress")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity_safe_guarantee_wv.clearHistory()
        val viewGroup = activity_safe_guarantee_wv.parent as ViewGroup
        viewGroup.removeView(activity_safe_guarantee_wv)
        activity_safe_guarantee_wv.destroy()
    }

}
