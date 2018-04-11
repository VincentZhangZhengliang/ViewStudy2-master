package com.topzrt.viewstudy2.ui.account.bean

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */

data class HxAccountBalanceBean(var status: String, var msg: String, var data: HxAccountBalanceDataBean)

data class HxAccountBalanceDataBean(var id: Int, var userId: String, var type: Int, var money: String, var lockMoney: String, var trueSubMoney: String)
