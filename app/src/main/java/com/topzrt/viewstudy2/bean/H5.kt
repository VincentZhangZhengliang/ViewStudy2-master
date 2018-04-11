package com.topzrt.viewstudy2.bean

/**
 * Created by Vincent;
 * Created on 2017/12/21;
 * DSC:
 */

data class H5DataBean(var title: String, var url: String)

data class H5Bean(var status: Int, var msg: String, var data: H5DataBean)