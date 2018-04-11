package com.topzrt.viewstudy2.accountService

import com.topzrt.viewstudy2.bean.Token
import com.topzrt.viewstudy2.bean.User

/**
 * Created by Vincent;
 * Created on 2017/12/26;
 * DSC:
 */
interface AccountService {

    /**
     * @param paramAccountListener 账号切换/登录/登出的时候会触发回调,一般继承者都需要addListener
     */
    fun addListener(paramAccountListener: AccountListener)

    /**
     * 弹出登录窗口
     *
     * @param paramLoginResultListener
     */
    fun doLoginAction(paramLoginResultListener: LoginResultListener)

    /**
     * 登出操作
     */
    fun dologoutAction()

    /**
     * 移除账号回调
     *
     * @param paramAccountListener
     */
    fun removeListener(paramAccountListener: AccountListener)

    fun removeLoginResultListener()

    /**
     * 弹出注册账号
     *
     * @param paramLoginResultListener
     */
    fun signup(paramLoginResultListener: LoginResultListener)

    /**
     * 登录的token
     *
     * @return
     */
    fun token(): String

    /**
     * 登录的sid
     *
     * @return
     */
    fun sid(): String

    /**
     * 登录的手机
     *
     * @return
     */
    fun phoneNum(): String

    /**
     * 是否登录
     *
     * @return
     */
    fun isLogin(): Boolean

    /**
     * token 是否过期
     *
     * @return
     */
    fun isTokenExpired(): Boolean

    /**
     * token 更新
     *
     * @return
     */
    fun refreshToken(token: Token)

    /**
     * User 返回一个User对象
     *
     * @return
     */
    fun user(): User

    /**
     * userinfo 更新
     *
     * @return
     */
    fun refreshUserInfo(user: User?)

    /**
     * 刷新Userinfo
     */
    fun refreshUserInfo()

}