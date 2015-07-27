package com.uestc.mymoa.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageSearchActivity extends BaseActivity{
    private Button but_edi_save;
    private ListView searchList;
    private BaseAdapter listnoteAdapter;
    private List<Map<String,Object>> mylistNotes=null;


    @Override
    protected void initLayout() {
        but_edi_save=(Button)findViewById(R.id.file_btn_edi_save);
        searchList=(ListView)findViewById(R.id.lv_filemanage_searchfile);
    }

    @Override
    protected void initListener() {
        but_edi_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchList.setAdapter(getAdapter());
            }

        });

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
                c.moveToPosition(position);

                int file_fileid = 0;

                //int mId = c.getInt(c.getColumnIndex(DBHelperContract.NOTE_COLUMN._ID));
                startActivity(FileManageDetailActivity.createInent(FileManageSearchActivity.this, file_fileid));
            }
        });

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_search;
    }
    private BaseAdapter getAdapter(){
        listnoteAdapter=new SimpleAdapter(this,mylistNotes,R.layout.layout_filemanage_search_item,new String[]{"title"},new int[]{R.id.file_lv_tv_search});
        return listnoteAdapter;
    }
}
