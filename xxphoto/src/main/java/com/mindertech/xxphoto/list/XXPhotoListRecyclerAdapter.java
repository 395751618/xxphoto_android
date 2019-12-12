package com.mindertech.xxphoto.list;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhihu.matisse.internal.entity.Item;

import java.util.ArrayList;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 10:10
 * @description 描述
 */
public class XXPhotoListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements XXPhotoListItemListener {

    private static final int VIEW_TYPE_CAPTURE = 0x01;
    private static final int VIEW_TYPE_MEDIA = 0x02;
    private Context mContext;
    private final Drawable mPlaceholderDrawable;
    private XXPhotoListListener listener;
    private Cursor mCursor;
    private int mRowIDColumn;
    private ArrayList<Item> otherSelectedPhotoList = new ArrayList<>();
    private ArrayList<Item> selectedPhotoList = new ArrayList<>();

    public XXPhotoListRecyclerAdapter(Context context, Drawable drawable, XXPhotoListListener listener) {
        this.mContext = context;
        this.mPlaceholderDrawable = drawable;
        this.listener = listener;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CAPTURE) {
            return new XXPhotoListRecyclerCameraViewHolder(XXPhotoListRecyclerCameraViewHolder.creator(parent));
        }
        if (viewType == VIEW_TYPE_MEDIA) {
            return new XXPhotoListRecyclerPictureViewHolder(XXPhotoListRecyclerPictureViewHolder.creator(parent), listener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof XXPhotoListRecyclerPictureViewHolder) {
            final Item item = Item.valueOf(mCursor);

            int status = 0;
            int count = 0;
            boolean isOtherselectedPhoto = false;
            boolean isSelectedPhoto = false;

            for (int i = 0;i < otherSelectedPhotoList.size();i ++) {
                Item item1 = otherSelectedPhotoList.get(i);
                if (item.id == item1.id) {
                    isOtherselectedPhoto = true;
                    break;
                }
            }
            for (int i = 0;i < selectedPhotoList.size();i ++) {
                Item item1 = selectedPhotoList.get(i);
                if (item.id == item1.id) {
                    isSelectedPhoto = true;
                    count = i + 1;
                    break;
                }
            }

            if (isOtherselectedPhoto) {
                status = 2;
            }
            if (isSelectedPhoto) {
                status = 1;
            }

            XXPhotoListRecyclerPictureViewHolder viewItem = (XXPhotoListRecyclerPictureViewHolder) holder;
            viewItem.bindMedia(mContext, mPlaceholderDrawable, item, status, (count == 0 ? "" : String.valueOf(count)));
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

    @Override
    public void swapCursor(Cursor cursor) {
        if (cursor == mCursor) {
            return;
        }
        if (cursor != null) {
            mCursor = cursor;
            mRowIDColumn = mCursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            notifyItemRangeRemoved(0, getItemCount());
            mCursor = null;
            mRowIDColumn = -1;
        }
    }

    @Override
    public void swapSelectedPhoto(ArrayList<Item> items) {
        if (null == items) {
            return;
        }
        selectedPhotoList = items;
        notifyDataSetChanged();
    }

    @Override
    public void swapOtherSelectedPhoto(ArrayList<Item> items) {
        if (null == items) {
            return;
        }
        otherSelectedPhotoList = items;
        notifyDataSetChanged();
    }

    public boolean hasData() {
        if (null == mCursor) {
            return false;
        }
        return true;
    }
}
