package com.mindertech.xxphoto.list;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhihu.matisse.internal.entity.Item;

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

    private int mRowIDColumn;

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
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Could not move cursor to position " + position
                    + " when trying to get item view type.");
        }
        return Item.valueOf(mCursor).isCapture() ? VIEW_TYPE_CAPTURE : VIEW_TYPE_MEDIA;
    }

    @Override
    public long getItemId(int position) {
        if (!isDataValid(mCursor)) {
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Could not move cursor to position " + position
                    + " when trying to get an item id");
        }

        return mCursor.getLong(mRowIDColumn);
    }

    private boolean isDataValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }

    public void swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return;
        }
        if (newCursor != null) {
            mCursor = newCursor;
            mRowIDColumn = mCursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            notifyItemRangeRemoved(0, getItemCount());
            mCursor = null;
            mRowIDColumn = -1;
        }
    }
}
