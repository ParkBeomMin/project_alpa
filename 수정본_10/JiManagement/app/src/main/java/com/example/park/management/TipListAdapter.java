package com.example.park.management;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jisung on 2017. 2. 11..
 */

public class TipListAdapter extends BaseAdapter {
    private Context context;
    private List<Tip> TipList;

    public TipListAdapter(Context context, List<Tip> TipList) {
        this.context = context;
        this.TipList = TipList;
    }

    @Override
    public int getCount() {
        return TipList.size();
    }

    @Override
    public Object getItem(int position) {
        return TipList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.tip,null);
        TextView titleText =(TextView) v.findViewById(R.id.title2Text);
        TextView nameText =(TextView) v.findViewById(R.id.name2Text);
        TextView dateText =(TextView) v.findViewById(R.id.date3Text);
        //TextView dataText = (TextView) v.findViewById(R.id.data3Text);
        titleText.setText(TipList.get(position).getTitle());
        nameText.setText(TipList.get(position).getName());
        dateText.setText(TipList.get(position).getDate().substring(5,16));
        //dataText.setText(TipList.get(position).getData());
        v.setTag(TipList.get(position).getTitle());
        return v;
    }
}
