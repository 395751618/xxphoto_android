package com.mindertech.xxphoto.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.mindertech.xxphoto.R;
import com.mindertech.xxphoto.R2;
import com.mindertech.xxphoto.list.XXPhotoListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.main
 * @anthor xiangxia
 * @time 2019-12-06 13:50
 * @description 描述
 */
public class XXPhotoMainUI extends FragmentActivity implements ViewPager.OnPageChangeListener {

    @BindView(R2.id.vp_content)
    ViewPager vpContent;
    @BindView(R2.id.fl_container)
    FrameLayout flContainer;
    @BindView(R2.id.tv_back)
    TextView tvBack;
    @BindView(R2.id.tv_album)
    TextView tvAlbum;
    @BindView(R2.id.layout_choose_album)
    LinearLayout layoutChooseAlbum;
    @BindView(R2.id.tv_upload)
    TextView tvUpload;
    @BindView(R2.id.bottom_toolbar)
    FrameLayout bottomToolbar;
    @BindView(R2.id.iv_pre)
    ImageView ivPre;
    @BindView(R2.id.iv_next)
    ImageView ivNext;
    @BindView(R2.id.tv_page_title)
    TextView tvPageTitle;
    @BindView(R2.id.tv_page_subtitle)
    TextView tvPageSubtitle;

    private XXPhotoFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xxmain);
        ButterKnife.bind(this);

        tvAlbum.setText("fdsafa");
        ArrayList<Fragment>pageList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Fragment fragment = new Fragment();
            if (i == 1) {

            }
            pageList.add(fragment);
        }
        pagerAdapter = new XXPhotoFragmentPagerAdapter(getSupportFragmentManager(), pageList);
        vpContent.setAdapter(pagerAdapter);
    }

    @OnClick({R2.id.iv_pre, R2.id.iv_next, R2.id.tv_back, R2.id.tv_upload, R2.id.tv_album})
    public void onTouchClick(View view) {
        if (view.equals(ivPre)) {

        } else if (view.equals(ivNext)) {

        } else if (view.equals(tvBack)) {

        } else if (view.equals(tvUpload)) {

        } else if (view.equals(tvAlbum)) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#008577"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
