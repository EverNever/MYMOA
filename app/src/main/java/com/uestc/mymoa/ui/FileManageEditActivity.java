package com.uestc.mymoa.ui;

import android.content.Context;
import android.content.Intent;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageEditActivity extends BaseActivity{
    private String file_edit_text;

    @Override
    protected void initLayout() {
        //Intent intent_edit=new Intent(FileManageEditActivity.this,FileManageDetailActivity.class);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return 0;
    }
    public static Intent editIntent(Context context,int text_id){
        Intent intent=new Intent();
        intent.setClass(context,FileManageEditActivity.class);
        return intent;
    }
}
