package com.topzrt.viewstudy2.ui.finance_activity.bean

/**
 * Created by Vincent;
 * Created on 2018/1/29;
 * DSC:
 */
//{"status":1,"msg":"OK","data":{"dealcate":
// [{"cate_name":"全部","cate_id":-1},{"cate_name":"体验标","cate_id":88},{"cate_name":"抵房贷","cate_id":"3,4,10"},{"cate_name":"抵车贷","cate_id":"2"},{"cate_name":"企业贷","cate_id":"8"},{"cate_name":"其它贷","cate_id":"12,11,6"}],
// "loantype":[{"loan_name":"全部","loan_type":-1,"desc_url":""},{"loan_name":"按月付息到期还本","loan_type":1,"desc_url":"http:\/\/dwww.topzrt.com\/\/app\/help_month_income.html"},{"loan_name":"到期还本+收益","loan_type":2,"desc_url":"http:\/\/dwww.topzrt.com\/\/app\/help_clearning.html"},{"loan_name":"体验标","loan_type":88,"desc_url":""}],"
// repaytime":[{"time_name":"全部","time":-1},{"time_name":"30天以内（含30天）","time":30},{"time_name":"30天-3个月（含3个月）","time":90},{"time_name":"3个月-6个月（含6个月）","time":180},{"time_name":"6个月以上","time":181}],
// "dealstatus":[{"status_name":"全部","status":-1},{"status_name":"即将发布","status":0},{"status_name":"正在投标","status":1},{"status_name":"正在回款","status":4}]}}

class CatTypeListBean(var status: Int, var msg: String, var data: CatTypeListDataBean)

class CatTypeListDataBean(var dealcate: List<DealCateBean>, var dealstatus: List<DealStatusBean>, var loantype: List<LoanTypeBean>, var repaytime: List<RepayTimeBean>)

class DealCateBean(var cate_id: String, var cate_name: String)

class DealStatusBean(var status: Int, var status_name: String)

class LoanTypeBean(var loan_type: Int, var loan_name: String)

class RepayTimeBean(var time: Int, var time_name: String)