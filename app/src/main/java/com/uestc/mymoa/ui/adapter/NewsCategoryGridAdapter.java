package com.uestc.mymoa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class NewsCategoryGridAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;
    private LayoutInflater inflater;
    private LinearLayout itemLinear;

    public NewsCategoryGridAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        list = getListData();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return itemLinear;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_news_category, null);
            itemLinear = (LinearLayout) convertView;
        }
        TextView newsCategoryText = (TextView) convertView.findViewById(R.id.newsCategoryText);
        newsCategoryText.setText(list.get(position));

        AbsListView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT,
<<<<<<< HEAD
                ((context).getResources().getDisplayMetrics().widthPixels - 4 * 3) / 3);
=======
                (context.getResources().getDisplayMetrics().widthPixels - 4 * 3) / 3);
>>>>>>> 7e73fe1b8604c8aa190ded8b331f9e542704c36c

        convertView.setLayoutParams(params);
        return convertView;
    }

    private List<String> getListData() {
        List<String> list = new ArrayList<>();
        list.add("娱乐");//id -- 1
        list.add("军事");//2
        list.add("科技");//2
        list.add("体育");//2
        return list;
    }
}
