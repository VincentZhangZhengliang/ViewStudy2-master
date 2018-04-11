package com.topzrt.viewstudy2.fragment


import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.bean.LoginBean
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.utils.CommonUtils
import com.topzrt.viewstudy2.utils.FileUtils
import com.topzrt.viewstudy2.utils.L
import com.zhy.http.okhttp.callback.StringCallback
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_register.*
import okhttp3.Call
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : BaseFragment() {

    override fun initView() {
    }

    override fun initListener() {
        //控制按钮是否可以点击
        checkParams(phone, password, verify_code, btn_get_verify_code, btn_register)
        //获取验证码
        btn_get_verify_code.setOnClickListener {
            getVerifyCode()
        }
        //注册
        btn_register.setOnClickListener {
            register()
        }
        //控制推荐人是否显示
        tv_referee.setOnClickListener {
            if (til_referee.visibility == View.VISIBLE) {
                til_referee.visibility = View.GONE
                referee.setText("")
            } else til_referee.visibility = View.VISIBLE
        }

    }

    private fun register() {
        val mobile = phone.text.toString()
        val user_pwd = password.text.toString()
        val user_pwd_confirm = password.text.toString()
        val mobile_code = verify_code.text.toString()
        var referer = referee.text.toString()
        L.e(msg = "mobile = $mobile , user_pwd = $user_pwd, user_pwd_confirm = $user_pwd_confirm, mobile_code = $mobile_code, referer = $referer")

        ReApiImpl.register(activity, mobile, user_pwd, user_pwd_confirm, mobile_code, referer).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext {
            if (it.status == 0) Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
        }.observeOn(Schedulers.io()).flatMap {
            if (it.status == 1) {
                ReApiImpl.login(activity, mobile, user_pwd)
            } else {
                Observable.error(object : Throwable(it.msg) {})
            }
        }.observeOn(Schedulers.io()).doOnNext {
            FileUtils.saveLogin(activity, it.data)
            MyApplication.instance.setUser(it.data)
        }.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<LoginBean> {

            override fun onNext(t: LoginBean) {
                if (t.status == 1) {
                    Toast.makeText(activity, "注册成功", Toast.LENGTH_SHORT).show()
                    activity.finish()
                }
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                L.e("Vincent", "onerror ;  ${e.message}")
            }

            override fun onSubscribe(d: Disposable) {

            }
        })
    }

    /**
     * 获取验证码
     */
    private fun getVerifyCode() {
        ReApiImpl.getIp(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                Log.e("Vincent", "getIP : " + response)
                val start = response?.indexOf("[")
                val end = response?.indexOf("]")
                val ip = response?.substring(start!!.plus(1), end!!)
                val mobile = phone.text.toString()
                val pwd = password.text.toString()
                L.e(msg = "mobile = $mobile , pwd = $pwd , ip = $ip")
                ReApiImpl.sendRegisterCode(activity, "", mobile, ip!!).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
                    Log.e("Vincent", "sendRegisterCode : status = ${it.status} , msg = ${it.msg} , errorcode = ${it.errorCode}")
                    Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.layout_register
    }

    /**
     * 控制按钮是否可以点击
     */
    private fun checkParams(phone: TextInputEditText, password: TextInputEditText, verify_code: TextInputEditText, btn_get_verify_code: Button, btn_register: Button) {
        //手机号的监听
        phone.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_get_verify_code.isEnabled = CommonUtils.isPhoneLegal(p0?.toString()) && CommonUtils.isPasswordLegal(password.text.toString())
                btn_register.isEnabled = CommonUtils.isPhoneLegal(phone.text.toString()) && CommonUtils.isPasswordLegal(password.text.toString()) && !TextUtils.isEmpty(verify_code.text.toString())
            }
        })
        //密码的监听
        password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_get_verify_code.isEnabled = CommonUtils.isPasswordLegal(p0?.toString()) && CommonUtils.isPhoneLegal(phone.text.toString())
                btn_register.isEnabled = CommonUtils.isPhoneLegal(phone.text.toString()) && CommonUtils.isPasswordLegal(password.text.toString()) && !TextUtils.isEmpty(verify_code.text.toString())
            }
        })

        //验证码的监听
        verify_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_register.isEnabled = CommonUtils.isPhoneLegal(phone.text.toString()) && CommonUtils.isPasswordLegal(password.text.toString()) && !TextUtils.isEmpty(verify_code.text.toString())
            }
        })
    }
}
