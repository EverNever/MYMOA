package com.uestc.mymoa.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uestc.mymoa.R;

import java.util.HashMap;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class ContactDetailActivity extends Activity {

    private TextView nameText;
    private TextView phonenumText;
    private Button delcontactButton;
    private int uid;

    private HashMap<String, Object> getData(){
        //TODO
        HashMap<String, Object> map = new HashMap<String, Object>();
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", -1);
        return map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.layout_contactdetail);
        nameText = (TextView) findViewById(R.id.nameText);
        phonenumText = (TextView) findViewById(R.id.phonenumText);
        delcontactButton = (Button) findViewById(R.id.delcontactButton);

        delcontactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(ContactDetailActivity.this)
                        .setTitle("删除联系人")
                        .setMessage("确定要删除该联系人吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
    @Override
    protected void onResume() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map = getData();
        if(Integer.parseInt(map.get("uid").toString()) != -1){
            nameText.setText(map.get("uname").toString());
            phonenumText.setText(map.get("phonenum").toString());
        }
    }
}
