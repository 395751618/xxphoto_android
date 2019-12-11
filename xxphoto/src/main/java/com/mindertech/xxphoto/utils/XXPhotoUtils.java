package com.mindertech.xxphoto.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.utils
 * @anthor xiangxia
 * @time 2019-12-06 11:46
 * @description 描述
 */
public final class XXPhotoUtils {

    public static String XXPHOTO_PARAM_LIST = "list";
    public static String XXPHOTO_PARAM_CURRENT_INDEX = "index";
    public static  String[] permissions1 = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static String[] permissions2 = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Object mObject;
    private XXPhotoPermissionsRequestListener mListener;

    private static class Holder {
        private static XXPhotoUtils INSTANCE = new XXPhotoUtils();
    }

    private XXPhotoUtils() {

    }

    public static XXPhotoUtils getInstance() {
        return Holder.INSTANCE;
    }

    public static void request(@NonNull Object object, @NonNull Context context, @NonNull String[] permissions, XXPhotoPermissionsRequestListener listener) {

        XXPhotoUtils.getInstance().mObject = null;
        XXPhotoUtils.getInstance().mObject = object;
        XXPhotoUtils.getInstance().mListener = null;
        XXPhotoUtils.getInstance().mListener = listener;

        List<String> unPermissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //判断是否有权限，便利出没有的权限
            for (String perm : permissions) {
                if (ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_DENIED) {
                    unPermissions.add(perm);
                }
            }
        }

        int requestCode = 1;
        if (unPermissions.size() == 0) {
            //都有这个权限
            invoke(object, requestCode, new ArrayList(Arrays.asList(permissions)), unPermissions);
        } else {
            if (object instanceof android.app.Activity) {
                ActivityCompat.requestPermissions((Activity) object, permissions, requestCode);
            } else if (object instanceof Fragment) {
                ((Fragment) object).requestPermissions(permissions, requestCode);
            }
        }
    }

    public static void handle(@NonNull Object object, @NonNull int requestCode,
                              @NonNull String[] permissions, @NonNull int[] grantResults) {
        ArrayList<String> granted = new ArrayList<>();
        ArrayList<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }
        invoke(object, requestCode, granted, denied);
    }

    private static void invoke(@NonNull Object object, @NonNull int requestCode,
                               @NonNull List<String> granted, @NonNull List<String> denied) {

        XXPhotoPermissionsRequestListener handler = XXPhotoUtils.getInstance().mListener;
        if (handler != null) {
            if (!denied.isEmpty()) {//拒绝
                handler.denied();
            }
            if (!granted.isEmpty()) {//授权
                handler.granted();
            }
            if (!granted.isEmpty() && denied.isEmpty()) {//完全授权
                handler.invoke();
            }
        }
    }

    public interface XXPhotoPermissionsRequestListener {
        public void invoke();
        public void denied();
        public void granted();
    }
}
