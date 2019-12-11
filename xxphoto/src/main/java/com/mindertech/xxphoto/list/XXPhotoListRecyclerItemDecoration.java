package com.mindertech.xxphoto.list;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 10:07
 * @description 描述
 */
public class XXPhotoListRecyclerItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(10, 10, 10, 10);
    }
}
