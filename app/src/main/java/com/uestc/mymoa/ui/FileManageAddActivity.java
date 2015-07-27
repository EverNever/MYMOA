package com.uestc.mymoa.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uestc.mymoa.R;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageAddActivity extends BaseActivity{
    private Button btn_cre_save;
    private EditText file_add_title;
    private EditText file_add_text;

    @Override
    protected void initLayout() {
        btn_cre_save=(Button)findViewById(R.id.file_btn_cre_save);
        file_add_title=(EditText)findViewById(R.id.file_cre_et_title);
        file_add_text=(EditText)findViewById(R.id.file_cre_et_text);

    }

    @Override
    protected void initListener() {
        btn_cre_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FileManageAddActivity.this,FileManageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return 0;
    }
}
