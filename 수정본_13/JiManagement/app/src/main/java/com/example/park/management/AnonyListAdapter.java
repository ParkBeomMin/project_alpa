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

public class AnonyListAdapter extends BaseAdapter {
    private Context context;
    private List<Anony> anonyList;

    public AnonyListAdapter(Context context, List<Anony> anonyList) {
        this.context = context;
        this.anonyList = anonyList;
    }

    @Override
    public int getCount() {
        return anonyList.size();
    }

    @Override
    public Object getItem(int position) {
        return anonyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.anony,null);
        TextView titleText =(TextView) v.findViewById(R.id.titleText);
        //TextView nameText =(TextView) v.findViewById(R.id.DataText);
        TextView dateText =(TextView) v.findViewById(R.id.date2Text);
        titleText.setText(anonyList.get(position).getTitle());
        //nameText.setText(anonyList.get(position).getData());
        dateText.setText(anonyList.get(position).getDate().substring(5,16));
        v.setTag(anonyList.get(position).getTitle());
        return v;
    }
}
