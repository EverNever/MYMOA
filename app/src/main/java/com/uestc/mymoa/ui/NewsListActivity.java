package com.uestc.mymoa.ui;

import android.content.Intent;
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
public class NewsListActivity extends BaseActivity {

    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();

    @Override
    protected void initLayout() {
        ListView lv=(ListView)this.findViewById(R.id.newsList);
    }

    @Override
    protected void initListener() {
        //需要绑定的数据
        //每一行的布局//动态数组中的数据源的键对应到定义布局的View中new

        ListView lv=(ListView)this.findViewById(R.id.newsList);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter
                (this,listItem, R.layout.layout_item, new String[] {"titleText01","managerText01", "dateText01","item_Image"},
        new int[] {R.id.titleText01,R.id.managerText01,R.id.dateText01,R.id.item_Image});
    lv.setAdapter(mSimpleAdapter);//为lv绑定适配器
//    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                long arg3) {
//            setText("你点击了第" + arg2 + "行");//设置标题栏显示点击的行
//        }
// });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        Intent intent=new Intent(NewsListActivity.this,NewsContentActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    @Override
    protected void initValue() {
        /*在数组中存放数据*/
        for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("titleText01", "第"+i+"行");
            map.put("item_Image", R.mipmap.ic_launcher);//加入图片
            map.put("managerText01","张三");
            map.put("dateText01", "2015-07-28 16:13:25");
            listItem.add(map);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_listview;
    }
}
