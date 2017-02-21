package com.example.park.management;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    ListView classListView;
    ListAdapter adapter;
    Context mContext;
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

}
