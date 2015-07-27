package com.uestc.mymoa.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
public class FileManageActivity extends BaseActivity{

    private Button but_list_add;
    private Button but_list_search;
    private ListView noteList;
    private int file_note_id;
    private String file_note_title;
    //private List<Map<String,Object>> file_list_listmap;
    private ArrayAdapter<String> filelistAdapter;

    /**
     * 获取数据
     * */
    private List<String> getAllfileNotes(){
        List<String> list=new ArrayList<>();

        /***/
        return list;
    }
    @Override
    protected void initLayout() {
        noteList=(ListView)findViewById(R.id.lv_filemanage_file);
    }
    @Override
    protected void initListener() {
        /**
         * 点击进入详情页
         * */
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
                c.moveToPosition(position);
/**
 * 获取文档id
 * */
                int file_fileid=0;
                //int mId = c.getInt(c.getColumnIndex(DBHelperContract.NOTE_COLUMN._ID));
                startActivity(FileManageDetailActivity.createInent(FileManageActivity.this, file_fileid));
            }
        });

        /**
         * 长按删除
         * */
        noteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               // Cursor c = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
               // c.moveToPosition(position);
                /**
                 * 获取删除文档id
                 * **/
                //final String messageId = c.getInt(c.getColumnIndex(DBHelperContract.NOTE_COLUMN._ID));

                new AlertDialog.Builder(FileManageActivity.this)
                        .setTitle("delete?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /**
                                 * 删除操作
                                 * */
                                setAdapter();
                            }
                        })
                        .setNegativeButton("N", null).create()
                        .show();
                return true;
            }
        });
    }

    private BaseAdapter getAdapter(){
        filelistAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getAllfileNotes());
        return filelistAdapter;
    }
    @Override
    protected void initValue() {
    }
    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filemanage_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * action bar操作
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_addnew:
                Intent intent_add=new Intent(FileManageActivity.this,FileManageAddActivity.class);
                startActivity(intent_add);
                break;
            case R.id.action_search:
                Intent intent_search=new Intent(FileManageActivity.this,FileManageSearchActivity.class);
                startActivity(intent_search);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setAdapter(){
        /****/
    }

}
