package com.topzrt.viewstudy2.ui.signup.bean

/**
 * Created by Vincent;
 * Created on 2018/1/26;
 * DSC:
 */
//{"status":1,"msg":"数据获取成功","data":{"sign":1,"sign_score":"5","all_day":31,"day_month":1,"day_weed":"1","day":26,"yiqian":[26],
// "weiqian":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25],
// "weiqian_count":25,"res":[26],"count_lianxu":1,"lianxu":1,"reward4Status":0,"reward3Status":0,
// "reward2Status":0,"reward1Status":0,"sign_gift_condition":0,"sign_gift_yiling":0,"opportunity":0,"date":"2018-01-26 10:08:55","score":"49941405"}}

class IntegralListBean(var status: Int, var msg: String, var data: IntegralListDataBean)

class IntegralListDataBean(var sign: Int, var sign_score: Int, var all_day: Int, var day_month: Int, var day_weed: Int, var day: Int, var yiqian: List<Int>, var weiqian: List<Int>, var weiqian_count: Int, var res: List<Int>, var count_lianxu: Int, var lianxu: Int, var reward4Status: Int, var reward3Status: Int, var reward2Status: Int, var reward1Status: Int, var sign_gift_condition: Int, var sign_gift_yiling: Int, var opportunity: Int, var date: String, var score: Long)