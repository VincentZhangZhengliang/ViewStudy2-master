package com.topzrt.viewstudy2.ui.setting.bean

/**
 * Created by Vincent;
 * Created on 2018/1/19;
 * DSC:
 */
data class UcCenterBean(var status: Int, var msg: String, var data: UcCenterDataBean)

data class UcCenterDataBean(var credit_status: Int, var credit_show: String,
                            var load_earnings: String, var user_name: String, var mobile: String,
                            var real_name: String, var customer_service: String,
                            var true_sub_money: String, var load_wait_earnings: String,
                            var rjmoney_sum: String, var total_money: String, var money: String,
                            var gestures_status: Int, var gestures_passwd: String,
                            var no_read_count: String, var user_red_sum: String, var cid: String,
                            var repay_list: List<UcCenterRepayListBean>, var sumpany_start: Int)

data class UcCenterRepayListBean(var name: String, var u_load_money: String, var load_id: String,
                                 var repay_date: String)
