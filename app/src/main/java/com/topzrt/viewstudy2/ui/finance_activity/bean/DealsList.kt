package com.topzrt.viewstudy2.ui.finance_activity.bean

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */


//{"status":1,"msg":"OK",
// "data":{"now_time":"2018-01-29 13:43:17","item":[{"id":"4286","name":"体验标641","sub_name":"体验标20180012901","cate_id":"2","deal_type":"1","rate":"12.00","rate_foramt_w":"12.00%","last_time":"1517191200","need_money":"2.00万","borrow_amount_format":"60.00万","borrow_amount":"600000.00","repay_time_type":"0","repay_time":"1","load_money":580000,"is_delete":"0","start_time":"2018-01-29 10:00:00","is_wait":0,"need_credit":"","remain_time":591403,"deal_status":"1","min_loan_money":"0.00","remain_time_format":"6天20时16分","loantype_format":"一次性还款","cg_type":"1","week":"","left_time":"2018-01-29 10:00:00","total_status":2}],"page":{"page":1,"page_total":1,"page_size":10,"total":"1"},"program_title":"投资列表"}}
class DealsListBean(var status: Int, var msg: String, var data: DealsListDataBean)

class DealsListDataBean(var now_time: String, var program_title: String, var item: List<DealsListItemBean>, var page: DealsListPageBean)

class DealsListItemBean(var borrow_amount: Double, var borrow_amount_format: String, var cate_id: Int, var cg_type: Int, var deal_status: Int, var deal_type: Int, var id: String, var is_delete: Int, var is_wait: Int, var last_time: String, var left_time: String, var load_money: Double, var loantype_format: String, var min_loan_money: Double, var name: String, var need_credit: String, var need_money: String, var rate: Double, var rate_foramt_w: String, var remain_time: Long, var remain_time_format: String, var repay_time: Long, var repay_time_type: Int, var start_time: String, var sub_name: String, var total_status: Int, var week: String)

class DealsListPageBean(var page: Int, var page_size: Int, var page_total: Int, var total: Int)

