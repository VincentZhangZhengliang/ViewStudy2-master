package com.topzrt.viewstudy2.ui.signup

import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.ui.signup.bean.ContinuousSignUpDataBean
import com.topzrt.viewstudy2.ui.signup.bean.IntegralListDataBean
import com.topzrt.viewstudy2.ui.signup.bean.SignUpBean
import com.topzrt.viewstudy2.ui.signup.presenter.SignUpPresenter
import com.topzrt.viewstudy2.ui.signup.view.ISignUpView
import com.topzrt.viewstudy2.utils.L
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 签到页面
 */
class SignUpActivity : BaseActivity(), ISignUpView {

    val presenter = SignUpPresenter(this@SignUpActivity)

    override fun continuousSignUp(continuousSignUpDataBean: ContinuousSignUpDataBean) {

    }

    override fun integralList(integralListDataBean: IntegralListDataBean) {
        L.e("integralList ++++++++++++++++")
    }

    override fun signUp(signUpBean: SignUpBean) {
        toast(signUpBean.msg)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }

        activity_sign_up_btn_sign_up.setOnClickListener {
            if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
                presenter.signUp(this, MyApplication.instance.mUser!!._Sessionkey)
            } else {
                toast("您还未登录~~~")
            }
        }

        activity_sign_up_ll_sign_up_7.setOnClickListener {
            continuousSignUp(1)
        }

        activity_sign_up_ll_sign_up_14.setOnClickListener {
            continuousSignUp(2)
        }

        activity_sign_up_ll_sign_up_21.setOnClickListener {
            continuousSignUp(3)
        }

        activity_sign_up_ll_sign_up_30.setOnClickListener {
            continuousSignUp(4)
        }

    }

    /**
     * 连续签到
     */
    private fun continuousSignUp(gift_type: Int) {
        if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
            presenter.signReward(this, MyApplication.instance.mUser!!._Sessionkey, gift_type.toString())
        } else {
            toast("您还未登录~~~")
        }
    }

    override fun initData() {
        super.initData()
        if (MyApplication.instance.mUser != null && MyApplication.instance.mUser?._Sessionkey != null) {
            presenter.integralList(this, MyApplication.instance.mUser!!._Sessionkey)
        } else {
            toast("您还未登录~~~")
        }
    }

    override fun initView() {
        super.initView()
        toolbar_right.text = "去补签"
        toolbar_title.text = "签到"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_sign_up
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_sign_up_v).init()
    }

}
