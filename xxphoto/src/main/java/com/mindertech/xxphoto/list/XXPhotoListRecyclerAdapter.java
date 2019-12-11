package com.mindertech.xxphoto.list;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 10:10
 * @description 描述
 */
public class XXPhotoListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CAPTURE = 0x01;
    private static final int VIEW_TYPE_MEDIA = 0x02;
    private Context mContext;
    private final Drawable addDrawable;
    private Cursor mCursor;

    public XXPhotoListRecyclerAdapter(Context context, Drawable drawable) {
        this.mContext = context;
        this.addDrawable = drawable;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CAPTURE) {
            return new XXPhotoListRecyclerCameraViewHolder(XXPhotoListRecyclerCameraViewHolder.creator(parent));
        }
        if (viewType == VIEW_TYPE_MEDIA) {
            return new XXPhotoListRecyclerPictureViewHolder(XXPhotoListRecyclerPictureViewHolder.creator(parent));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof XXPhotoListRecyclerPictureViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        if (isDataValid(mCursor)) {
            return mCursor.getCount();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private boolean isDataValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }
}
