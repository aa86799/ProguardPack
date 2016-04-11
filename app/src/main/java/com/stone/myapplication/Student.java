package com.stone.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * author : stone
 * time   : 15/4/10 21 54
 * email  : aa86799@163.com
 */
public class Student implements Serializable, Parcelable {
    private String name;

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    private Student(Parcel in) {
        this.name = in.readString();
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getString() {
        return this.name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

}
