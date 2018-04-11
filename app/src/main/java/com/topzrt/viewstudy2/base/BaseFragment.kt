package com.topzrt.viewstudy2.base


import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.utils.Constants


/**
 * Fragment基类
 */
abstract class BaseFragment : Fragment() {

    var isViewCreated: Boolean = false
    var isUIVisible: Boolean = false
    var imei: String = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isViewCreated = true
        lazyLoad()

        return inflater!!.inflate(getLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
    }

    fun lazyLoad() {
        if (isViewCreated && isUIVisible) {
            initData()
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isUIVisible = false
        }
    }


    open fun initData() {
        if (hasPermission(arrayOf(android.Manifest.permission.READ_PHONE_STATE))) imei = MyApplication.instance.getImei()
        else requestPermission(Constants.READ_PHONE_STATE, arrayOf(android.Manifest.permission.READ_PHONE_STATE))
    }

    abstract fun initView()
    abstract fun initListener()
    abstract fun getLayout(): Int


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
            if (ContextCompat.checkSelfPermission(activity, permission.toString()) != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }


    /**
     * 请求权限
     */
    protected fun requestPermission(code: Int, permissions: Array<out String>) {
        ActivityCompat.requestPermissions(activity, permissions, code)

    }

    /**
     * 权限申请后的操作
     */

    open fun onPermissionResult() {

    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
        }
    }
}
