package com.example.park.management;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        TextView freeText =(TextView) v.findViewById(R.id.freeText);
        //TextView dataText = (TextView) v.findViewById(R.id.dataText);
        TextView nameText =(TextView) v.findViewById(R.id.nameText);
        TextView dateText =(TextView) v.findViewById(R.id.dateText);
        freeText.setText(freeList.get(position).getTitle());
        //dataText.setText(freeList.get(position).getData());
        nameText.setText(freeList.get(position).getName());
        dateText.setText(freeList.get(position).getDate());
        v.setTag(freeList.get(position).getTitle());
        return v;
    }

}
