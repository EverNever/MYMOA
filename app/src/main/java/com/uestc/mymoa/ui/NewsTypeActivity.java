package com.uestc.mymoa.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DBJ_MAC on 2015/7/27.
 */
public class NewsTypeActivity extends BaseActivity {
    @Override
    protected void initLayout() {
        Button btn=(Button)this.findViewById(R.id.backButton);
        ListView lv=(ListView)this.findViewById(R.id.newsList);
        TextView nt=(TextView)this.findViewById(R.id.newsText);
    }

    @Override
    protected void initListener() {
        //需要绑定的数据
        //每一行的布局//动态数组中的数据源的键对应到定义布局的View中new
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
        ListView lv=(ListView)this.findViewById(R.id.newsList);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter
                (this,listItem, R.layout.layout_item, new String[] {"titleText01","managerText01", "dateText01","item_Image"},
        new int[] {R.id.titleText01,R.id.managerText01,R.id.dateText01,R.id.item_Image});
    lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
        long arg3) {
            setTitle("你点击了第"+arg2+"行");//设置标题栏显示点击的行
        }
    });
    }

    @Override
    protected void initValue() {
        /*在数组中存放数据*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("titleText01", "第"+i+"行");
            map.put("item_Image", R.mipmap.ic_launcher);//加入图片
            map.put("managerText01","张三");
            map.put("dateText", "2015-07-28 16:13:25");
            listItem.add(map);
        }
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_listview;
    }
}
