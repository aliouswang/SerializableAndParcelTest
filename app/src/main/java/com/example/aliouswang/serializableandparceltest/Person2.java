package com.example.aliouswang.serializableandparceltest;

import android.os.Parcel;
import android.os.Parcelable;

public class Person2 implements Parcelable {

    public int age;
    public String name;

    public Person2(int age, String name) {
        this.age = age;
        this.name = name;
    }


    protected Person2(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel in) {
            return new Person2(in);
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }
}
