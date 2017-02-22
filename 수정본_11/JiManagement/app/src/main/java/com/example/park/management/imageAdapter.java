package com.example.park.management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.media.ThumbnailUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by jisung on 2017. 2. 22..
 */

public class imageAdapter extends BaseAdapter {
    private Context mContext;
    private Path mBasePath;

    public imageAdapter(Context mContext, Path mBasePath) {
        this.mContext = mContext;
        this.mBasePath = mBasePath;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Bitmap bm;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
/*
        bm = BitmapFactory.decodeFile(mBasePath + File.separator + mImgList[position]);//path
        Bitmap mThumbnail = ThumbnailUtils.extractThumbnail(bm, 300, 300);

        imageView.setPadding(8, 8, 8, 8);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
        imageView.setImageBitmap(mThumbnail);
  */      return imageView;
    }

}
