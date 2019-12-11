package com.mindertech.xxphoto_android;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mindertech.xxphoto.bean.XXPhotoPageBean;
import com.mindertech.xxphoto.main.XXPhotoMainUI;
import com.mindertech.xxphoto.utils.XXPhotoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_text)
    public void onTextClick(View view) {

        XXPhotoUtils.request(this, getApplicationContext(), XXPhotoUtils.permissions1, new XXPhotoUtils.XXPhotoPermissionsRequestListener() {
            @Override
            public void invoke() {
                ArrayList<XXPhotoPageBean> list = new ArrayList<>();
                for (int i = 0;i < 3; i++) {
                    XXPhotoPageBean bean = new XXPhotoPageBean();
                    bean.title = "title:" + String.valueOf(i);
                    bean.subtitle = "subtitle:" + String.valueOf(i);
                    bean.type = 1;
                    bean.count = 3;
                    bean.key = String.valueOf(i) + "category";
                    list.add(bean);
                }
                XXPhotoMainUI.openPhoto(getApplicationContext(), MainActivity.this, list, 0);
            }

            @Override
            public void denied() {
                Toast toast=Toast.makeText(MainActivity.this,"未授权",Toast.LENGTH_SHORT    );
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

            @Override
            public void granted() {
                System.out.println("-");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#008577"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        XXPhotoUtils.handle(this, requestCode, permissions, grantResults);
    }
}
