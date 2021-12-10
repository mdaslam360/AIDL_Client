package com.aslam.aidl_client;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    int number;

    public User(int number) {
        this.number = number;
    }

    protected User(Parcel in) {
        number = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getNumber() {
        return number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
    }
}
