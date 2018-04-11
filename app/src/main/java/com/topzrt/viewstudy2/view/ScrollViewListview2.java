package com.topzrt.viewstudy2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Vincent;
 * Created on 2017/12/18;
 * DSC:
 */

public class ScrollViewListview2 extends ListView {
    public ScrollViewListview2(Context context) {
        super(context);
    }

    public ScrollViewListview2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewListview2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
