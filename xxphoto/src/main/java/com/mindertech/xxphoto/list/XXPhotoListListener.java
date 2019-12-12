package com.mindertech.xxphoto.list;

import android.database.Cursor;

import com.mindertech.xxphoto.bean.XXPhotoPageBean;
import com.zhihu.matisse.internal.entity.Item;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-12 13:38
 * @description 描述
 */
public interface XXPhotoListListener {

    public void onSelectedItem(XXPhotoListFragment fragment, XXPhotoPageBean bean, Item item, boolean selected);

    public Cursor onPhotoList(XXPhotoListFragment fragment, XXPhotoPageBean bean);
}
