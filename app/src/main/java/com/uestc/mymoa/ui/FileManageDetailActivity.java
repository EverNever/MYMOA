package com.uestc.mymoa.ui;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.uestc.mymoa.R;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageDetailActivity extends BaseActivity{
    private Button but_detail_edit;
    private int myfileId;
    @Override
    protected void initLayout() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filemanage_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static Intent createInent(Context context,int file_myid){

        Intent intent=new Intent();

        intent.setClass(context,FileManageDetailActivity.class);

        return intent;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
            //Intent intent_edit=new Intent(FileManageDetailActivity.this,FileManageEditActivity.class);
            startActivity(FileManageEditActivity.editIntent(FileManageDetailActivity.this,myfileId));
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
