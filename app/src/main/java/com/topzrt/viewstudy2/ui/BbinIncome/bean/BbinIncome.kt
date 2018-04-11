package com.topzrt.viewstudy2.ui.BbinIncome.bean

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */

//{"status":1,"msg":"",
// "data":{"load_list":[
// {"id":"16492",
// "name":"体验标522",
// "sub_name":"体验标20170101901",
// "deal_status":"5",
// "interest_money":"￥6.67",
// "money":"￥20000.00",
// "has_repay":"1",
// "update_date":"2017-11-13",
// "true_interest_money":"￥6.67",
// "has_pay":"0",
// "pay_date":"",
// "create_date":"2017-10-19",
// "yiji_time":"2017-10-20"}
// ],"response_code":1}}
class BbinIncomeBean(var status: Int, var msg: String, var data: BbinIncomeDataBean)

class BbinIncomeDataBean(var load_list: List<BbinIncomeListBean>, var response_code: Int)

class BbinIncomeListBean(var id: String, var name: String, var sub_name: String, var deal_status: Int, var interest_money: String, var money: String, var has_repay: Int, var update_date: String, var true_interest_money: String, var has_pay: Int, var pay_date: String, var create_date: String, var yiji_time: String)