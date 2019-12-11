package com.mindertech.xxphoto.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mindertech.xxphoto.R;
import com.mindertech.xxphoto.R2;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 10:28
 * @description 描述
 */
public class XXPhotoListRecyclerCameraViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public XXPhotoListRecyclerCameraViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.item_camera);
    }

    public static View creator(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_xxlist_item_camera, parent, false);
        return view;
    }
}
