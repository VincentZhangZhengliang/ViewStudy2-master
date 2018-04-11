package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/12/20;
 * DSC:
 */

data class FreshmanListBean(var title: String, var hurl: String, var img_src: String)

data class FreshmanDataBean(var list: List<FreshmanListBean>)

data class FreshmanBean(var status: Int, var msg: String, var data: FreshmanDataBean)
