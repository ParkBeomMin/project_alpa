package com.example.park.management;

/**
 * Created by jisung on 2017. 2. 11..
 */

public class Free {
    String title;
    String name;
    String date;

    public Free(String title, String name, String date) {
        this.title = title;
        this.name = name;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
