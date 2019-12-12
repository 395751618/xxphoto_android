package com.mindertech.xxphoto.list;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mindertech.xxphoto.R;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 10:28
 * @description 描述
 */
public class XXPhotoListRecyclerPictureViewHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;
    private RelativeLayout relativeLayout;
    private ImageView status1ImageView;
    private ImageView status2ImageView;
    private ImageView status3ImageView;
    private ImageView status4ImageView;
    private TextView countTextView;

    private Context mContext;
    private Item mMedia;
    private int mStatus;
    private XXPhotoListListener listener;

    public XXPhotoListRecyclerPictureViewHolder(View itemView, XXPhotoListListener listener) {
        super(itemView);
        this.listener = listener;

        thumbnail = (ImageView) itemView.findViewById(R.id.item_thumbnail);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_selected);
        status1ImageView = (ImageView) itemView.findViewById(R.id.iv_state_1);
        status2ImageView = (ImageView) itemView.findViewById(R.id.iv_state_2);
        status3ImageView = (ImageView) itemView.findViewById(R.id.iv_state_3);
        status4ImageView = (ImageView) itemView.findViewById(R.id.iv_state_4);
        countTextView = (TextView) itemView.findViewById(R.id.tv_count);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick();
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick();
            }
        });
    }

    public void onItemClick() {
        if (null != listener) {
            if (mStatus == 1) {
                listener.onSelectedItem(null, null, mMedia, false);
            } else
            {
                listener.onSelectedItem(null, null, mMedia, true);
            }
        }
    }

    public static View creator(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_xxlist_item_picture, parent, false);
        return view;
    }

    public void bindMedia(Context context, Drawable placeholder, Item item, int status, String count) {
        mContext = context;
        mMedia = item;
        mStatus = status;
        if (null == thumbnail) {
            return;
        }
        Uri uri = mMedia.getContentUri();
        if (null == uri) {
            return;
        }

        switch (status) {
            case 0://可选，未选
                relativeLayout.setEnabled(true);
                status1ImageView.setVisibility(View.VISIBLE);
                status2ImageView.setVisibility(View.GONE);
                status3ImageView.setVisibility(View.GONE);
                status4ImageView.setVisibility(View.GONE);
                countTextView.setText("");
                break;
            case 1://可选，选中
                relativeLayout.setEnabled(true);
                status1ImageView.setVisibility(View.GONE);
                status2ImageView.setVisibility(View. VISIBLE);
                status3ImageView.setVisibility(View.GONE);
                status4ImageView.setVisibility(View.GONE);
                countTextView.setText(count);
                break;
            case 2://不可选
                relativeLayout.setEnabled(false);
                status1ImageView.setVisibility(View.GONE);
                status2ImageView.setVisibility(View.GONE);
                status3ImageView.setVisibility(View.VISIBLE);
                status4ImageView.setVisibility(View.VISIBLE);
                countTextView.setText("");
                break;
            default:
                relativeLayout.setEnabled(false);
                status1ImageView.setVisibility(View.GONE);
                status2ImageView.setVisibility(View.GONE);
                status3ImageView.setVisibility(View.GONE);
                status4ImageView.setVisibility(View.GONE);
                countTextView.setText("");
                break;

        }

        new PicassoEngine().loadImage(mContext, 200, 200, thumbnail, item.getContentUri());
    }
}
