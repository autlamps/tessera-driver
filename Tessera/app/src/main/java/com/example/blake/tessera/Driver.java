package com.example.blake.tessera;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Driver {
    @SerializedName("name")
    @Expose
    public String mName;
    @SerializedName("id")
    @Expose
    public Integer mId;

    public Driver(String name, Integer id) {
        mName = name;
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    @Override
    public String toString() {
        return mName + " " + mId;
    }
}

