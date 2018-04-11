package com.topzrt.viewstudy2.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * Created by Vincent;
 * Created on 2017/12/11;
 * DSC:
 */
open class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView?.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(context).load(path).into(imageView)

    }
}