package com.topzrt.viewstudy2.ui.dealdetail.bean

/**
 * Created by Vincent;
 * Created on 2018/1/19;
 * DSC:
 */


data class DealsInfoDealBean(var need_money: Double, var id: Int, var name: String,
                             var sub_name: String, var cate_id: Int, var rate: Double,
                             var varrate_foramt_w: String, var borrow_amount: Double,
                             var loantype: Int, var borrow_amount_format: String,
                             var repay_time_type: Int, var repay_time: String,
                             var load_money: String, var is_delete: Int, var start_time: String,
                             var is_wait: Int, var need_credit: Int, var remain_time: String,
                             var deal_status: Int, var min_loan_money: Double,
                             var remain_time_format: String, var deal_type: Int,
                             var last_time: String, var loantype_format: String,
                             var left_time: String, var deal_time: String, var way_novice: Int,
                             var cg_type: Int)


data class DealsInfoHongBaoBean(var id: String, var user_id: String, var deal_id: Int,
                                var money: Int, var sdate: String, var edate: String,
                                var is_use: Int, var use_date: String, var load_id: Int,
                                var use_load_id: Int, var use_deal_id: Int,
                                var min_use_money: String, var money_type: Int, var memo: String,
                                var is_pc: Int, var is_new: Int, var is_msg: String,
                                var use_days: Int, var min_qx: Int, var danwei: Int,
                                var is_actrb: Int, var qixian: String)

data class DealsInfoDataBean(var sumpany_start: Int, var hongbao_count: Int,
                             var hongbao: List<DealsInfoHongBaoBean>, var is_faved: Int,
                             var now_time: String, var open_ips: Int, var ips_acct_no: String,
                             var ips_bill_no: String, var user_money: String,
                             var user_money_format: String, var week: String,
                             var show_rewarded: String, var award_type: Int,
                             var min_investment_amount: Int, var interval_cost: Int,
                             var ifswitch: Int, var program_title: String, var interest_sum: String,
                             var load_money_sum: Double, var award_state: Int,
                             var risk_notice: String, var fxtitle: String, var fxurl: String,
                             var fxcontent: String, var frist_t: Int, var frist_msg: String,
                             var deal: DealsInfoDealBean)

data class DealsInfoBean(var status: Int, var msg: String, var data: DealsInfoDataBean)