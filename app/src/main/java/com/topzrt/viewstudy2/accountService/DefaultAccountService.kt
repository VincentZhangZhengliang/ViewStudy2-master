package com.topzrt.viewstudy2.accountService

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import java.util.*

/**
 * Created by Vincent;
 * Created on 2017/12/26;
 * DSC:
 */
class DefaultAccountService(context: Context) : BaseAccountService(context) {

    private val listeners = ArrayList<AccountListener>()
    private var loginResultListener: LoginResultListener? = null

    private fun preferences(paramContext: Context): SharedPreferences {
        return paramContext.getSharedPreferences(paramContext.packageName, Context.MODE_PRIVATE)
    }


    override fun addListener(paramAccountListener: AccountListener) {
        if (paramAccountListener != null) this.listeners.add(paramAccountListener)

    }

    override fun dispatchAccountChanged() {
        if (this.loginResultListener != null) {
            this.loginResultListener?.onLoginSuccess(this)
            this.loginResultListener = null
        }
        for (listener in this.listeners) {
            listener.onAccountChanged(this)
        }
    }

    override fun dispatchProfileChanged() {
        for (listener in this.listeners) {
            listener.onProfileChanged(this)
        }
    }

    /**
     * 登录
     */
    override fun doLoginAction(paramLoginResultListener: LoginResultListener) {

    }

    override fun removeListener(paramAccountListener: AccountListener) {
        this.listeners.remove(paramAccountListener)
    }

    override fun removeLoginResultListener() {
        this.loginResultListener = null
    }

    override fun signup(paramLoginResultListener: LoginResultListener) {
        val localIntent = Intent()
        localIntent.data = Uri.parse("wuheyou://signup")
        localIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this.context.startActivity(localIntent)
        this.loginResultListener = paramLoginResultListener
    }


}