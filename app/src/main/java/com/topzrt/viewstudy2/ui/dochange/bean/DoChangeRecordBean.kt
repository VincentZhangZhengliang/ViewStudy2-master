package com.topzrt.viewstudy2.ui.dochange.bean

/**
 * Created by Vincent;
 * Created on 2018/1/24;
 * DSC:
 */
data class DoChangeRecordBean(var status: Int, var msg: String, var data: DoChangeRecordDataBean)

data class DoChangeRecordDataBean(var page: DoChangeRecordPageBean, var list: List<DoChangeRecordListBean>)

data class DoChangeRecordListBean(var attr: String, var attr_stock_id: Int, var delivery_addr: String, var delivery_date: String, var delivery_express: String, var delivery_name: String, var delivery_sn: String, var delivery_tel: String, var delivery_time: String, var ex_date: String, var ex_time: String, var goods_id: Int, var goods_name: String, var id: String, var img: String, var is_delivery: String, var memo: String, var num: Long, var number: Long, var order_sn: String, var order_status: Int, var score: String, var total_score: String, var user_id: String)

data class DoChangeRecordPageBean(var page: Int, var page_size: Int, var page_total: Int, var total: Int)