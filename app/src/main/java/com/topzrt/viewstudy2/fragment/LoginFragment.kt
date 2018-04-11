package com.topzrt.viewstudy2.fragment


import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.utils.FileUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment() {

    override fun initView() {

    }

    override fun initListener() {
        btn_login.setOnClickListener {
            val mobile = username.text
            val pwd = password.text
            if (!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(pwd)) {
                ReApiImpl.login(activity, mobile.toString(), pwd.toString()).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).doOnNext {
                    if (it.status == 1) {
                        FileUtils.saveLogin(activity, it.data)
                        MyApplication.instance.setUser(it.data)
                    }
                }.observeOn(AndroidSchedulers.mainThread()).subscribe {
                    if (it.status == 1) {
                        activity.finish()
                    }
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.layout_login
    }

}
