package com.example.park.management;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.park.management.R;

/**
 * Created by user on 2017-01-11.
 */

public class AnnoucementLayout extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.annoucement_layout, container, false);
        return v;
    }

}
