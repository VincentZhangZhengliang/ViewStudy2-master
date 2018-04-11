package com.topzrt.viewstudy2.ui.setting.bean

/**
 * Created by Vincent;
 * Created on 2018/1/19;
 * DSC:   华兴存管开关
 */

//{"status":1,
// "msg":"",
// "data":{"param_id":"64",
// "param_type":"hxcg_switch",
// "param_name":"华兴存管",
// "param_code":"0",
// "param_value":"1",
// "remark":"",
// "status":"1",
// "edit_user":"技术部",
// "edit_date":"2018-01-10 10:29:21",
// "create_date":"2017-12-29 15:32:47"}}

data class HxcgStatusBean(var status: String, var msg: String, var data: HxcgStatusDataBean)

data class HxcgStatusDataBean(var param_id: Int, var param_type: String, var param_name: String,
                              var param_code: Int, var param_value: Int, var remark: String,
                              var status: Int, var edit_user: String, var edit_date: String,
                              var create_date: String)