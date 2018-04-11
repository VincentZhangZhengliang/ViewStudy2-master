package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/12/21;
 * DSC:    消息详情Bean
 */


data class NoticeDetailDataLastBean(var id: String, var title: String)

data class NoticeDetailDataDataBean(var add_admin_id: String, var brief: String,
                                    var cate_id: String, var click_count: String,
                                    var content: String, var create_time: String, var from: String,
                                    var icon: String, var id: String, var is_delete: String,
                                    var is_effect: String, var is_recommendation: String,
                                    var online_time: String, var rel_url: String,
                                    var seo_description: String, var seo_keyword: String,
                                    var seo_title: String, var sort: String, var source: String,
                                    var sub_title: String, var tag: String, var title: String,
                                    var uname: String, var update_admin_id: String,
                                    var update_time: String)

data class NoticeDetailDataBean(var data: NoticeDetailDataDataBean,
                                var last: NoticeDetailDataLastBean)

data class NoticeDetailBean(var status: String, var msg: String, var data: NoticeDetailDataBean)

