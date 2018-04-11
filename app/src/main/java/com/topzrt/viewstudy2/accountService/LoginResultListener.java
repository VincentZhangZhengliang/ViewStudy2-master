package com.topzrt.viewstudy2.accountService;

/**
 * 项目: Wuheyou
 * 版本: 1.0
 * 作者：sea on 2015/11/14 11:58
 * 说明:
 * 登录结果回调
 * 当一个界面需要登录之后才能看的时候,打开之后会去登录界面.这个时候登录结果需要通过好这个回调通知打开登录界面的view进行刷新操作
 */
public interface LoginResultListener {

    /**
     * 取消登录
     *
     * @param paramAccountService
     */
    void onLoginCancel( AccountService paramAccountService);

    /**
     * 登录成功
     *
     * @param paramAccountService
     */
    void onLoginSuccess(AccountService paramAccountService);

    /**
     * 登录失败
     *
     * @param paramAccountService
     */
    void onLoginError(AccountService paramAccountService);
}