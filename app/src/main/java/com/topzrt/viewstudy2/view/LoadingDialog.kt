package com.topzrt.viewstudy2.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.topzrt.viewstudy2.R
import kotlinx.android.synthetic.main.loading.*

/**
 * Created by Vincent;
 * Created on 2017/12/20;
 * DSC:
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.MyDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading)
        setCanceledOnTouchOutside(false)
    }

    fun stopAnimator() {
        loading_rl.stopAnimator()
    }

}