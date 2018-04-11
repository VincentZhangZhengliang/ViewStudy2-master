package com.topzrt.viewstudy2.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.bean.NoticeDetailBean
import com.topzrt.viewstudy2.utils.Api
import com.topzrt.viewstudy2.view.LoadingDialog
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_my_web.*
import kotlinx.android.synthetic.main.activity_notice_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import okhttp3.Call
import java.lang.Exception

/**
 * 消息详情页面
 */
class NoticeDetailActivity : BaseActivity() {

    lateinit var title: String
    var id: Int = -1

    override fun getLayoutId(): Int {
        return R.layout.activity_notice_detail
    }

    override fun initData() {
        super.initData()
        title = intent.getStringExtra("title")
        id = intent.getIntExtra("id", -1)

        Api.getNoticeDetail(id.toString(), object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                Log.e("Vincent", response)
                val gson = Gson()
                val noticeDetailBean = gson.fromJson<NoticeDetailBean>(response, NoticeDetailBean::class.java)

                activity_notice_detail_tv_title.text = noticeDetailBean.data.data.title
                activity_notice_detail_tv_date.text = "日期：${noticeDetailBean.data.data.create_time}"

                setData(noticeDetailBean.data.data.content)

            }

            override fun onError(call: Call?, e: Exception?, id: Int) {

            }
        })
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_notice_detail_v).init()
    }

    override fun initView() {
        super.initView()
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_right.visibility = View.GONE
    }

    private fun setData(content: String) {
        val settings = activity_notice_detail_wv.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.useWideViewPort = false
        settings.loadWithOverviewMode = true
        settings.blockNetworkImage = false
        settings.pluginState = WebSettings.PluginState.ON
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        activity_notice_detail_wv.webViewClient = MyWebViewClient()
        activity_notice_detail_wv.webChromeClient = MyWebChromeClient()
        activity_notice_detail_wv.setDownloadListener(MyWebViewDownLoadListener())
        var h5String = content
        if (h5String.contains("<img")) {
            h5String = h5String.replace("<img", "<img  width=\"300px\"")
        }
        if (h5String.contains("class=\"MsoNormalTable\"")) {
            h5String = h5String.replace("class=\"MsoNormalTable\"", "width=\"10px\"")
        }
        activity_notice_detail_wv.loadData(h5String, "text/html; charset=UTF-8", null)
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }
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

        var loadingDialog: LoadingDialog = LoadingDialog(this@NoticeDetailActivity)

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            loadingDialog.stopAnimator()
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

    override fun onDestroy() {
        super.onDestroy()
        val parent = activity_notice_detail_wv.parent
        if (parent != null) {
            (parent as ViewGroup).removeView(activity_notice_detail_wv)
        }
        activity_notice_detail_wv.stopLoading()
        // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
        activity_notice_detail_wv.settings.javaScriptEnabled = false
        activity_notice_detail_wv.clearHistory()
        activity_notice_detail_wv.clearView()
        activity_notice_detail_wv.removeAllViews()
        activity_notice_detail_wv.destroy()

    }

    override fun onStop() {
        super.onStop()
        activity_notice_detail_wv.removeAllViews()
        activity_notice_detail_wv.destroy()
    }


}
