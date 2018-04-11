package com.topzrt.viewstudy2.ui.integralmall.bean

/**
 * Created by Vincent;
 * Created on 2018/1/23;
 * DSC:
 */


data class IntegralMallBean(var status: Int, var msg: String, var data: IntegralMallDataBean)

data class IntegralMallDataBean(var info: String, var is_sign: String, var new_integral_switch: Int, var score: String, var user_login_status: Int, var list: List<IntegralMallListBean>, var scoreTotal: String)

data class IntegralMallListBean(var id: String, var name: String, var sub_name: String, var cate_id: String, var img: String, var brief: String, var description: String, var sort: Int, var max_bought: Long, var user_max_bought: Long, var score: Long, var is_delivery: Int, var is_hot: Int, var is_new: Int, var is_recommend: Int, var seo_title: String, var seo_keyword: String, var seo_description: String, var goods_type_id: Int, var invented_number: String, var buy_number: Long, var off_shelf: Long, var is_limit: Int, var begin_time: String, var end_time: String, var old_score: Long)