package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/12/20;
 * DSC:
 */


data class NoticeListBean(var brief: String, var content: String, var create_time: String,
                          var from: String, var id: Int, var is_read: Int, var title: String,
                          var type_id: Int)

data class PageBean(var page: Int, var page_size: Int, var page_total: Int)

data class NoticeDataBean(var list: List<NoticeListBean>, var page: PageBean)

data class NoticeBean(var status: Int, var msg: String, var data: NoticeDataBean)