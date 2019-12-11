package com.mindertech.xxphoto.bean;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @project xxphoto_android
 * @package：com.mindertech.xxphoto.bean
 * @anthor xiangxia
 * @time 2019-12-11 16:51
 * @description 描述
 */
public final class XXPhotoPageBean implements Parcelable {

    public String title;
    public String subtitle;
    public int count;
    public int type;
    public String key;
    public ArrayList<Uri> uriArrayList = new ArrayList<>();

    public XXPhotoPageBean() {
    }

    protected XXPhotoPageBean(Parcel in) {
        title = in.readString();
        subtitle = in.readString();
        count = in.readInt();
        type = in.readInt();
        key = in.readString();
        uriArrayList = in.createTypedArrayList(Uri.CREATOR);
    }

    public static final Creator<XXPhotoPageBean> CREATOR = new Creator<XXPhotoPageBean>() {
        @Override
        public XXPhotoPageBean createFromParcel(Parcel in) {
            return new XXPhotoPageBean(in);
        }

        @Override
        public XXPhotoPageBean[] newArray(int size) {
            return new XXPhotoPageBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeInt(count);
        dest.writeInt(type);
        dest.writeString(key);
        dest.writeTypedList(uriArrayList);
    }
}
