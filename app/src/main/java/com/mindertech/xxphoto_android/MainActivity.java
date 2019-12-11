package com.mindertech.xxphoto_android;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mindertech.xxphoto.main.XXPhotoMainUI;
import com.mindertech.xxphoto.utils.XXPhotoUtils;

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
        Intent intent = new Intent(this, XXPhotoMainUI.class);

        XXPhotoUtils.request(this, getApplicationContext(), XXPhotoUtils.permissions1, new XXPhotoUtils.XXPhotoPermissionsRequestListener() {
            @Override
            public void invoke() {
                System.out.println("invoke");

                startActivity(intent);
            }

            @Override
            public void denied() {
                System.out.println("denied");
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
