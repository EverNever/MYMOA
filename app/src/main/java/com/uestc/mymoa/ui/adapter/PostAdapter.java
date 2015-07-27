package com.uestc.mymoa.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nothisboy on 2015/7/27.
 */
public class PostAdapter extends PagerAdapter {

    private Context context;
    private List<Map<String, Object>> list;
    private List<View> views = new ArrayList<>();

    public PostAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    public PostAdapter(Context context) {
        this.context = context;
        getImageList();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void getImageList() {
        list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("imageResId", R.drawable.ic_add_white_24dp);
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("imageResId", R.drawable.ic_account_box_white_24dp);
        list.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("imageResId", R.drawable.ic_assignment_white_24dp);
        list.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("imageResId", R.drawable.ic_widgets_white_24dp);
        list.add(map4);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.item_post, null);
        ImageView postImage = (ImageView) convertView.findViewById(R.id.postImage);

        postImage.setBackgroundResource((Integer)( list.get(position).get("imageResId")));

        container.addView(convertView);
        views.add(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(views.get(position));
    }


}
