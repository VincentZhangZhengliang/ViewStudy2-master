package com.topzrt.viewstudy2.accountService

/**
 * Created by Vincent;
 * Created on 2017/12/26;
 * DSC:
 */
interface AccountListener {
    /**
     * 账号切换的时候通知
     *
     * @param paramAccountService
     */
     fun onAccountChanged(paramAccountService: AccountService)

    /**
     * 账号个人信息改变的时候会通知
     *
     * @param paramAccountService
     */
     fun onProfileChanged(paramAccountService: AccountService)

}
