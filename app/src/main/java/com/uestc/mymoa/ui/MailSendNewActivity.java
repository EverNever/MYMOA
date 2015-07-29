package com.uestc.mymoa.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.MailSendMailHandler;
import com.uestc.mymoa.io.model.RequestStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/27.
 */
public class MailSendNewActivity extends BaseActivity {

    private Button sendButton;
    private EditText touidEdit;
    private EditText contentMailEdit;
    private MailSendMailHandler handler;

    @Override
    protected void initLayout() {
        sendButton = (Button) findViewById(R.id.sendButton);
        touidEdit = (EditText) findViewById(R.id.toidEdit);
        contentMailEdit = (EditText) findViewById(R.id.contentMailEdit);
    }

    @Override
    protected void initListener() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touidEdit.getText().length()!=11){
                    Toast.makeText(MailSendNewActivity.this,"要输入11位的电话号码哟！",Toast.LENGTH_SHORT).show();
                }else if (contentMailEdit.getText().length()==0){
                    Toast.makeText(MailSendNewActivity.this,"信息要有内容哟！",Toast.LENGTH_SHORT).show();
                }else {
                sendMail();
            }}
        });
    }

    @Override
    protected void initValue() {
        handler = new MailSendMailHandler();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_mail_send_new;
    }

    private void sendMail(){
        RequestParams params = new RequestParams();
        params.addBodyParameter("fromuid", Id.userId);
        params.addBodyParameter("touid", String.valueOf(touidEdit.getText()));
        params.addBodyParameter("time",getCurrentDate());
        params.addBodyParameter("content",String.valueOf(contentMailEdit.getText()));
        handler.process(params, new IOCallback() {
            @Override
            public void onStart() {
                Toast.makeText(MailSendNewActivity.this,"start！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(List result) {

            }

            @Override
            public void onSuccess(Object result) {

                if (((RequestStatus)result).code == 200) {
                    Toast.makeText(MailSendNewActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(MailSendNewActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
