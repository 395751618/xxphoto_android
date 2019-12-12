package com.mindertech.xxphoto.list;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindertech.xxphoto.R;
import com.mindertech.xxphoto.bean.XXPhotoPageBean;
import com.mindertech.xxphoto.main.XXPhotoMainUI;
import com.zhihu.matisse.internal.entity.Item;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 09:45
 * @description 描述
 */
public class XXPhotoListFragment extends Fragment implements XXPhotoListItemListener, XXPhotoListListener {

    private XXPhotoPageBean bean;
    private RecyclerView recyclerView;
    private XXPhotoListRecyclerAdapter adapter;
    private XXPhotoListListener listener;

    public XXPhotoListFragment(XXPhotoPageBean bean) {
        this.bean = bean;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xxlist, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_content);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(manager);

        XXPhotoListRecyclerItemDecoration itemDecoration = new XXPhotoListRecyclerItemDecoration();
        recyclerView.addItemDecoration(itemDecoration);

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.photo_add);
        adapter = new XXPhotoListRecyclerAdapter(getContext(), drawable, this);
        recyclerView.setAdapter(adapter);
    }

    public void setListListener(XXPhotoListListener listener) {
        this.listener = listener;
        Cursor cursor = listener.onPhotoList(this, bean);
        if (null != cursor) {
            swapCursor(cursor);
        }
    }

    public XXPhotoPageBean getPageBean() {
        return bean;
    }

    @Override
    public void swapCursor(Cursor cursor) {
        if (null != adapter) {
            adapter.swapCursor(cursor);
        }
    }

    @Override
    public void swapSelectedPhoto(ArrayList<Item> items) {
        if (null != adapter) {
            adapter.swapSelectedPhoto(items);
        }
    }

    @Override
    public void swapOtherSelectedPhoto(ArrayList<Item> items) {
        if (null != adapter) {
            adapter.swapOtherSelectedPhoto(items);
            if (false == adapter.hasData()) {
                Cursor cursor = listener.onPhotoList(this, bean);
                if (null != cursor) {
                    swapCursor(cursor);
                }
            }
        }
    }

    @Override
    public void onSelectedItem(XXPhotoListFragment fragment, XXPhotoPageBean bean, Item item, boolean selected) {
        if (null != listener) {
            listener.onSelectedItem(this, this.bean, item, selected);
        }
    }

    @Override
    public Cursor onPhotoList(XXPhotoListFragment fragment, XXPhotoPageBean bean) {
        return null;
    }
}
