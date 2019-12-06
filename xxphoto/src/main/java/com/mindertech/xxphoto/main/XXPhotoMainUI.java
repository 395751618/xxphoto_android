package com.mindertech.xxphoto.main;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.mindertech.xxphoto.R;
import com.mindertech.xxphoto.R2;

import butterknife.BindView;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.main
 * @anthor xiangxia
 * @time 2019-12-06 13:50
 * @description 描述
 */
public class XXPhotoMainUI extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xxmain);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
