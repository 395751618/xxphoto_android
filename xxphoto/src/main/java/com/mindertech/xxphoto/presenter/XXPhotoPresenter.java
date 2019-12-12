package com.mindertech.xxphoto.presenter;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.fragment.app.FragmentActivity;

import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.AlbumCollection;
import com.zhihu.matisse.internal.model.AlbumMediaCollection;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.presenter
 * @anthor xiangxia
 * @time 2019-12-12 09:56
 * @description 描述
 */
public class XXPhotoPresenter<V extends FragmentActivity> implements AlbumCollection.AlbumCallbacks, AlbumMediaCollection.AlbumMediaCallbacks {

    protected V mView;

    private XXPhotoModelListener listener;

    private AlbumCollection mAlbumCollection = new AlbumCollection();
    private Cursor albumList; //相册列表

    private AlbumMediaCollection mAlbumMediaCollection;
    public static final int request_code_capture = 500;
    private Album currentAlbum; //当前相册对象

    public void loadData(Bundle savedInstanceState) {
        mAlbumCollection.onCreate(mView, this);
        mAlbumCollection.onRestoreInstanceState(savedInstanceState);
        mAlbumCollection.loadAlbums();
    }

    /**
     * 数据回调
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:29
     */
    public void setPresenterListener(XXPhotoModelListener listener) {
        this.listener = listener;
    }

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:28
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:29
     */
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void onAlbumLoad(Cursor cursor) {
        this.albumList = cursor;

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                cursor.moveToPosition(mAlbumCollection.getCurrentSelection());
                Album album = Album.valueOf(cursor);
                if (null != listener) {
                    listener.onCurrentAlbumResponse(album);
                }
                if (album.isAll() && SelectionSpec.getInstance().capture) {
                    album.addCaptureCount();
                }
                onCurrentAlbumPhotoList(album);
            }
        });
    }

    @Override
    public void onAlbumReset() {

    }

    /**
     * 当前相册中图片列表
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:50
     */
    public void onCurrentAlbumPhotoList(Album album) {
        if (null != mAlbumMediaCollection) {
            mAlbumMediaCollection.onDestroy();
        }
        this.currentAlbum = album;
        mAlbumMediaCollection = new AlbumMediaCollection();
        mAlbumMediaCollection.onCreate(mView, this);
        mAlbumMediaCollection.load(album, true);
    }

    @Override
    public void onAlbumMediaLoad(Cursor cursor) {
        if (null != listener) {
            listener.onCurrentAlbumListResponse(cursor);
        }
    }

    @Override
    public void onAlbumMediaReset() {

    }

    /**
     * 相册列表是否加载完成
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:58
     */
    public boolean albumListLoaded() {
        if (null == albumList) {
            return false;
        }
        return true;
    }

    public Cursor albumList() {
        return this.albumList;
    }
}
