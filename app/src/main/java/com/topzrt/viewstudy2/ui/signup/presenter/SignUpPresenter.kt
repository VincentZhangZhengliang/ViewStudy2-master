package com.topzrt.viewstudy2.ui.signup.presenter

import android.content.Context
import com.topzrt.viewstudy2.ui.signup.biz.SignUpBiz
import com.topzrt.viewstudy2.ui.signup.view.ISignUpView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
class SignUpPresenter(var iView: ISignUpView) {

    val biz = SignUpBiz()

    fun signReward(context: Context, _Sessionkey: String, gift_type: String) {
        biz.signReward(context, _Sessionkey, gift_type).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.continuousSignUp(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun signUp(context: Context, _Sessionkey: String) {
        biz.signUp(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.signUp(it)
            } else {
                iView.toast(it.msg)
            }
        }
    }

    fun integralList(context: Context, _Sessionkey: String) {
        biz.integralList(context, _Sessionkey).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.status == 1) {
                iView.integralList(it.data)
            } else {
                iView.toast(it.msg)
            }
        }
    }

}