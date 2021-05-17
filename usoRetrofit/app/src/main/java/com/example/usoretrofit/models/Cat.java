package com.example.usoretrofit.models;

import android.graphics.Bitmap;

public class Cat {
    private Object _id;
    private Number _v;
    private String user;
    private String text;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public void set_v(Number _v) {
        this._v = _v;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object get_id() {
        return _id;
    }

    public Number get_v() {
        return _v;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}
