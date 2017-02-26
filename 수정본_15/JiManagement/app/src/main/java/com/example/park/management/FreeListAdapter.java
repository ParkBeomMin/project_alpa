package com.example.park.management;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by jisung on 2017. 2. 11..
 */

public class FreeListAdapter extends BaseAdapter {
    private Context context;
    private List<Free> freeList;

    public FreeListAdapter(Context context, List<Free> freeList) {
        this.context = context;
        this.freeList = freeList;
    }

    @Override
    public int getCount() {
        return freeList.size();
    }

    @Override
    public Object getItem(int position) {
        return freeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.free,null);
        ImageView profile = (ImageView) v.findViewById(R.id.profile);
        TextView freeText =(TextView) v.findViewById(R.id.freeText);
        TextView nameText =(TextView) v.findViewById(R.id.nameText);
        TextView dateText =(TextView) v.findViewById(R.id.dateText);
        //ImageView image = (ImageView) v.findViewById(R.id.profile);
        freeText.setText(freeList.get(position).getTitle());

        //dataText.setText(freeList.get(position).getData());
        nameText.setText(freeList.get(position).getName());
        dateText.setText(freeList.get(position).getDate().substring(5,16));
        v.setTag(freeList.get(position).getTitle());

        return v;
    }
    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
