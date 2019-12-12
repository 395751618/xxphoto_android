package com.mindertech.xxphoto.album;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindertech.xxphoto.R;
import com.zhihu.matisse.internal.entity.Album;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.album
 * @anthor xiangxia
 * @time 2019-12-12 10:02
 * @description 描述
 */
public class XXPhotoAlbumGridAdapter extends CursorAdapter {

    public XXPhotoAlbumGridAdapter(Context context, Cursor c) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_xxalbum_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Album album = Album.valueOf(cursor);

        ImageView iv_image = view.findViewById(R.id.item_photo);
        TextView tv_name = view.findViewById(R.id.item_name);

        String name = album.getDisplayName(context) + " (" + String.valueOf(album.getCount()) + ")";
        tv_name.setText(name);
    }
}
