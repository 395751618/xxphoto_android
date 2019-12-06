package com.mindertech.xxphoto_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mindertech.xxphoto.main.XXPhotoMainUI;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.tv_text)
    public void onTextClick(View view) {
        Intent intent = new Intent(this, XXPhotoMainUI.class);
        startActivity(intent);
    }

}
