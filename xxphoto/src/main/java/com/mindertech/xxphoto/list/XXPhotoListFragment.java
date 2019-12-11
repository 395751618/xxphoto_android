package com.mindertech.xxphoto.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindertech.xxphoto.R;

import butterknife.ButterKnife;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.list
 * @anthor xiangxia
 * @time 2019-12-11 09:45
 * @description 描述
 */
public class XXPhotoListFragment extends Fragment {

    private RecyclerView recyclerView;

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


    }
}
