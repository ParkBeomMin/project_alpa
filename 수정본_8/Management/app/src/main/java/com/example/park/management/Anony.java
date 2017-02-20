package com.example.park.management;

/**
 * Created by jisung on 2017. 2. 11..
 */

public class Anony {
    String title;
    String Data;
    String date;

    public Anony(String title, String data, String date) {
        this.title = title;
        Data = data;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
