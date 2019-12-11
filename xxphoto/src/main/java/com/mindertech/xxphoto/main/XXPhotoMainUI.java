package com.mindertech.xxphoto.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.mindertech.xxphoto.R;
import com.mindertech.xxphoto.R2;
import com.mindertech.xxphoto.bean.XXPhotoPageBean;
import com.mindertech.xxphoto.list.XXPhotoListFragment;
import com.mindertech.xxphoto.utils.XXPhotoUtils;

import java.util.ArrayList;

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
    @BindView(R2.id.layout_pre)
    LinearLayout layoutPre;
    @BindView(R2.id.layout_next)
    LinearLayout layoutNext;

    private ArrayList<XXPhotoPageBean> pageBeans;
    private int currentIndex = 0;
    private XXPhotoFragmentPagerAdapter pagerAdapter;

    /**
     * 打开图片选择器
     *
     * @param list 所有页面数据
     * @param currentIndex 当前显示的页面
     *
     * @author xiangxia
     * @createAt 2019-12-11 17:38
     */
    public static void openPhoto(Context context, Activity activity, ArrayList<XXPhotoPageBean> list, int currentIndex) {
        Intent intent = new Intent(context, XXPhotoMainUI.class);
        intent.putExtra(XXPhotoUtils.XXPHOTO_PARAM_LIST, list);
        intent.putExtra(XXPhotoUtils.XXPHOTO_PARAM_CURRENT_INDEX, currentIndex);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xxmain);
        ButterKnife.bind(this);

        currentIndex = getIntent().getIntExtra(XXPhotoUtils.XXPHOTO_PARAM_CURRENT_INDEX, 0);
        pageBeans = getIntent().getParcelableArrayListExtra(XXPhotoUtils.XXPHOTO_PARAM_LIST);

        ArrayList<XXPhotoListFragment> pageList = new ArrayList<>();
        for (int i = 0; i < pageBeans.size(); i++) {
            XXPhotoListFragment fragment = new XXPhotoListFragment();
            pageList.add(fragment);
        }
        pagerAdapter = new XXPhotoFragmentPagerAdapter(getSupportFragmentManager(), pageList);
        vpContent.setAdapter(pagerAdapter);
        vpContent.addOnPageChangeListener(this);
        if (currentIndex != 0) {
            vpContent.setCurrentItem(currentIndex, false);
        } else {
            refreshTopToolBar(currentIndex);
        }
    }

    private void refreshTopToolBar(int position) {
        if (currentIndex == position) {
            return;
        }
        if (0 == position && pageBeans.size() - 1 == position) {
            layoutPre.setVisibility(View.GONE);
            layoutNext.setVisibility(View.GONE);
        } else if (0 == position) {
            layoutPre.setVisibility(View.GONE);
            layoutNext.setVisibility(View.VISIBLE);
        } else if (pageBeans.size() - 1 == position) {
            layoutPre.setVisibility(View.VISIBLE);
            layoutNext.setVisibility(View.GONE);
        } else {
            layoutPre.setVisibility(View.VISIBLE);
            layoutNext.setVisibility(View.VISIBLE);
        }

        XXPhotoPageBean bean = pageBeans.get(position);
        tvPageTitle.setText(bean.title);
        tvPageSubtitle.setText(bean.subtitle);
    }

    @OnClick({R2.id.layout_pre, R2.id.layout_next, R2.id.tv_back, R2.id.tv_upload, R2.id.tv_album})
    public void onTouchClick(View view) {
        if (view.equals(layoutPre)) {
            if (0 == currentIndex) {
                return;
            }
            vpContent.setCurrentItem(currentIndex - 1, true);
        } else if (view.equals(layoutNext)) {
            if (pageBeans.size() - 1 == currentIndex) {
                return;
            }
            vpContent.setCurrentItem(currentIndex + 1, true);
        } else if (view.equals(tvBack)) {
            finish();
        } else if (view.equals(tvUpload)) {
            finish();
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
        refreshTopToolBar(position);
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
