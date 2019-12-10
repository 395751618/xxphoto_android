package com.mindertech.xxphoto.main;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.mindertech.xxphoto.R;
import com.mindertech.xxphoto.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.main
 * @anthor xiangxia
 * @time 2019-12-06 13:50
 * @description 描述
 */
public class XXPhotoMainUI extends FragmentActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xxmain);
        ButterKnife.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
