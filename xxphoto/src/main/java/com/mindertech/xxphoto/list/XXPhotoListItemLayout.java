package com.mindertech.xxphoto.list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-12 11:32
 * @description 描述
 */
public class XXPhotoListItemLayout extends FrameLayout {

    public XXPhotoListItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public XXPhotoListItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XXPhotoListItemLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
