package com.mindertech.xxphoto.album;

import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.fragment.app.FragmentActivity;

import com.mindertech.xxphoto.R;
import com.zhihu.matisse.internal.entity.Album;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.album
 * @anthor xiangxia
 * @time 2019-12-12 09:39
 * @description 描述
 */
public class XXPhotoAlbumUI extends PopupWindow implements OnItemClickListener {

    private Context mContext;
    private View view;
    private GridView gridView;
    private XXPhotoAlbumGridAdapter adapter;
    private XXPhotoAlbumListener listener;

    public XXPhotoAlbumUI(Context context) {
        this.mContext = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.layout_xxalbum, null);
        this.gridView = this.view.findViewById(R.id.gv_photo_selector);
        this.gridView.setOnItemClickListener(this);

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(800);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置弹出窗体的背景
//        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.anim_xxalbum_pop);
    }

    public void setAlbumListener(XXPhotoAlbumListener listener) {
        this.listener = listener;
    }

    public void setAlbums(Cursor cursor) {

        adapter = new XXPhotoAlbumGridAdapter(mContext, cursor);
        this.gridView.setAdapter(adapter);
    }

    public void show(FragmentActivity fragmentActivity) {
        View rootView = fragmentActivity.findViewById(android.R.id.content);
        if (rootView == null) {
            rootView = fragmentActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        }
        this.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public static void show(FragmentActivity fragmentActivity, Cursor cursor, XXPhotoAlbumListener listener) {
        XXPhotoAlbumUI albumUI = new XXPhotoAlbumUI(fragmentActivity.getBaseContext());
        albumUI.setAlbumListener(listener);
        albumUI.setAlbums(cursor);
        albumUI.show(fragmentActivity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != listener) {
            Cursor cursor = adapter.getCursor();
            cursor.moveToPosition(position);
            Album album = Album.valueOf(cursor);
            listener.onAlbumsSelectedItem(album);
            dismiss();
        }
    }
}
