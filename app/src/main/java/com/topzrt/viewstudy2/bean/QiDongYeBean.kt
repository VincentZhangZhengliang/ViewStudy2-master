package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/11/27;
 * DSC:
 */

data class QdyUrlBean(val img: String, val id: Int, val open_time: Int, val bir_open: Int, val beizhu: String)

data class Databean(val qdy_url: QdyUrlBean)

data class QiDongYeBean(val status: Int, val msg: String, val data: Databean)