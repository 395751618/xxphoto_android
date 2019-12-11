package com.mindertech.xxphoto.list;

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

    public XXPhotoListRecyclerPictureViewHolder(@NonNull View itemView) {
        super(itemView);
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

            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public static View creator(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_xxlist_item_picture, parent, false);
        return view;
    }
}
