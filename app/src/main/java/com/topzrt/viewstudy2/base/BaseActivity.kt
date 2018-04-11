package com.topzrt.viewstudy2.base

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.accountService.AccountListener
import com.topzrt.viewstudy2.accountService.AccountService
import com.topzrt.viewstudy2.accountService.LoginResultListener
import com.topzrt.viewstudy2.accountService.SIDTokenListener
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.utils.Constants
import com.topzrt.viewstudy2.utils.FileUtils
import com.topzrt.viewstudy2.utils.L


abstract class BaseActivity : AppCompatActivity(), LoginResultListener, SIDTokenListener, AccountListener {


    var mImmersionBar: ImmersionBar? = null
    var imei: String? = null

    @RequiresApi(Build.VERSION_CODES.N) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initImmersionBar()
        MyApplication.instance.setUser(FileUtils.getLogin(this@BaseActivity))
        initData()
        initView()
        initListener()
    }

    open fun initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this@BaseActivity)
        mImmersionBar?.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar?.destroy()
    }

    open fun initListener() {

    }

    open fun turnToActivity(context: Context, clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clazz)
        startActivity(intent)
    }

    open fun initData() {
        if (ContextCompat.checkSelfPermission(this@BaseActivity, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@BaseActivity, arrayOf(Manifest.permission.READ_PHONE_STATE), 100)
            Toast.makeText(this@BaseActivity, "如果拒绝授权,会导致应用无法正常使用", Toast.LENGTH_SHORT).show()
        } else {
            L.e("Vincent", "imei : $imei")
        }
    }

    open fun initView() {}

    abstract fun getLayoutId(): Int

    fun getScreenSize(): Pair<Int, Int> {
        val manager = this.windowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        val width = outMetrics.widthPixels
        val height = outMetrics.heightPixels
        return Pair(width, height)
    }

    /**
     *
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.READ_PHONE_STATE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionResult()
                }
            }
        }
    }

    /**
     * 判断是否拥有权限
     */
    fun hasPermission(vararg permissions: Array<out String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission.toString()) != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }

    /**
     * 请求权限
     */
    protected fun requestPermission(code: Int, permissions: Array<out String>) {
        ActivityCompat.requestPermissions(this, permissions, code)
    }

    /**
     * 权限申请后的操作
     */

    open fun onPermissionResult() {

    }

    override fun onLoginCancel(paramAccountService: AccountService) {
    }

    override fun onLoginSuccess(paramAccountService: AccountService) {

    }

    override fun onLoginError(paramAccountService: AccountService) {

    }

    override fun sidOverdue() {

    }

    override fun onAccountChanged(paramAccountService: AccountService) {

    }

    override fun onProfileChanged(paramAccountService: AccountService) {

    }

}
