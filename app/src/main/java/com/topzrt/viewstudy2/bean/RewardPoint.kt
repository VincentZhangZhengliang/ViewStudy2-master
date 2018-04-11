package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/12/21;
 * DSC:
 */
data class RewardPointBean(var status: String, var msg: String, var data: RewardPointDataBean)

data class RewardPointDataBean(var info: String, var is_sign: String,
                               var new_integral_switch: String, var score: String,
                               var scoreTotal: String, var user_login_status: String,
                               var list: List<RewardPointDataListBean>)

data class RewardPointDataListBean(var begin_time: String, var brief: String,
                                   var buy_number: String, var cate_id: String,
                                   var description: String, var end_time: String,
                                   var goods_type_id: String, var id: String, var img: String,
                                   var invented_number: String, var is_delivery: String,
                                   var is_hot: String, var is_limit: String, var is_new: String,
                                   var is_recommend: String, var max_bought: String,
                                   var name: String, var off_shelf: String, var old_score: String,
                                   var score: String, var seo_description: String,
                                   var seo_keyword: String, var seo_title: String, var sort: String,
                                   var sub_name: String, var user_max_bought: String)