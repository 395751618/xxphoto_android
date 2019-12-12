package com.mindertech.xxphoto.presenter;

import android.database.Cursor;

import com.zhihu.matisse.internal.entity.Album;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.presenter
 * @anthor xiangxia
 * @time 2019-12-12 10:40
 * @description 描述
 */
public interface XXPhotoModelListener {

    /**
     * 相册列表
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:48
     */
    public void onAlbumListResponse(Cursor cursor);

    /**
     * 当前相册对象
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:48
     */
    public void onCurrentAlbumResponse(Album album);

    /**
     * 当前相册中图片列表
     *
     * @author xiangxia
     * @createAt 2019-12-12 10:49
     */
    public void onCurrentAlbumListResponse(Cursor cursor);
}
