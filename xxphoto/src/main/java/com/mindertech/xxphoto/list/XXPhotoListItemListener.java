package com.mindertech.xxphoto.list;

import android.database.Cursor;

import com.zhihu.matisse.internal.entity.Item;

import java.util.ArrayList;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-12 13:50
 * @description 描述
 */
public interface XXPhotoListItemListener {

    public void swapCursor(Cursor cursor);

    public void swapSelectedPhoto(ArrayList<Item> items);

    public void swapOtherSelectedPhoto(ArrayList<Item> items);
}
