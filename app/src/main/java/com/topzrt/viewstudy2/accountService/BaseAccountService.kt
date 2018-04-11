package com.topzrt.viewstudy2.accountService

import android.content.Context
import android.content.SharedPreferences
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.bean.Token
import com.topzrt.viewstudy2.bean.User

/**
 * Created by Vincent;
 * Created on 2017/12/26;
 * DSC:
 */
abstract class BaseAccountService(context: Context) : AccountService {

    protected var context: Context = context
    private var prefs: SharedPreferences = context.getSharedPreferences("account", 0)

    abstract fun dispatchAccountChanged()

    abstract fun dispatchProfileChanged()

    override fun dologoutAction() {
        if (isLogin()) dispatchAccountChanged()
        this.prefs.edit().remove("sid").remove("_SessionKey").remove("exceedtime").apply()
    }

    override fun token(): String {
        return this.prefs.getString("_SessionKey", "")
    }

    override fun sid(): String {
        return this.prefs.getString("uid", "")
    }

    override fun phoneNum(): String {
        return this.prefs.getString("phone_num", "")
    }

    override fun isLogin(): Boolean {
        return token().isNotEmpty()
    }

    /**
     * 拿不到exceedtime时间的时候,那么就返回现在的时间
     *
     * @return
     */
    fun tokenExpired(): Long {
        return this.prefs.getLong("exceedtime", System.currentTimeMillis() / 1000)
    }

    /**
     * 过期时间小于现在的时间,那么就是过期了 服务器精确到秒
     *
     * @return
     */
    override fun isTokenExpired(): Boolean {
        var isExpired = false
        if (isLogin()) {
            if (tokenExpired() < System.currentTimeMillis() / 1000) {
                isExpired = true
            }
        }
        return isExpired
    }

    /**
     * 更新 token
     *
     * @return
     */
    override fun refreshToken(token: Token) {
        this.prefs.edit().putString("uid", token.uid).putString("_SessionKey", token._SessionKey).putLong("exceedtime", token.expired).apply()
    }

    override fun user(): User {
        return User(prefs.getString("_SessionKey", "")!!, prefs.getString("cid", "")!!, prefs.getString("gestures_passwd", "")!!, prefs.getString("gestures_status", "")!!, prefs.getString("idcardpassed", "")!!, prefs.getString("levels", "")!!, prefs.getString("mobile", "")!!, prefs.getString("no_read_mess", "")!!, prefs.getString("response_code", "")!!, prefs.getString("returns", "")!!, prefs.getString("show_err", "")!!, prefs.getString("title", "")!!, prefs.getString("uid", "")!!, prefs.getString("user_login_status", "")!!, prefs.getString("user_money", "")!!, prefs.getString("user_money_format", "")!!, prefs.getString("user_name", "")!!)
    }

    /**
     * 刷新用户信息
     *
     * @param user user
     */
    override fun refreshUserInfo(user: User?) {
        val edit = prefs.edit()
        edit.putString("_SessionKey", user?._Sessionkey).putString("cid", user?.cid).putString("gestures_passwd", user?.cid).putString("gestures_status", user?.cid).putString("idcardpassed", user?.cid).putString("levels", user?.cid).putString("mobile", user?.cid).putString("no_read_mess", user?.cid).putString("response_code", user?.cid).putString("returns", user?.cid).putString("show_err", user?.cid).putString("title", user?.cid).putString("uid", user?.cid).putString("user_login_status", user?.cid).putString("user_money", user?.cid).putString("user_money_format", user?.cid).putString("user_name", user?.cid).apply()
        dispatchProfileChanged()
        dispatchAccountChanged()
    }

    /**
     * 刷新用户信息
     */
    override fun refreshUserInfo() {
        refreshUserInfo(MyApplication.instance.getUser())
    }

}