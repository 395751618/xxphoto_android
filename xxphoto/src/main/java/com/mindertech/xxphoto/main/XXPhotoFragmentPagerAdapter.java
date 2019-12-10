package com.mindertech.xxphoto.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.main
 * @anthor xiangxia
 * @time 2019-12-10 17:49
 * @description 描述
 */
public class XXPhotoFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mData = new ArrayList<>();

    public XXPhotoFragmentPagerAdapter(@NonNull FragmentManager fm, ArrayList<T> list) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mData = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
