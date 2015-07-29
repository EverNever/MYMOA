package com.uestc.mymoa.ui;

import android.content.Intent;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.MailQueryMailContentHandler;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by nothisboy on 2015/7/29.
 */
public class MailItemContentActivity extends BaseActivity {

    private MailQueryMailContentHandler handler;

    private TextView fromuidText;
    private TextView timeText;
    private TextView contentText;

    @Override
    protected void initLayout() {
        fromuidText = (TextView) findViewById(R.id.fromuidText);
        timeText = (TextView) findViewById(R.id.timeText);
        contentText = (TextView) findViewById(R.id.contentMailText);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {
        handler = new MailQueryMailContentHandler();
        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_mail_content;
    }

    private void getData() {

        Intent intent = getIntent();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("mailid", intent.getStringExtra("mailid"));
        handler.process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {

            }

            @Override
            public void onSuccess(Object result) {
                fromuidText.setText((CharSequence) ((HashMap<String, Object>) result).get("fromuid"));
                timeText.setText((CharSequence) ((HashMap<String, Object>) result).get("time"));
                contentText.setText((CharSequence) ((HashMap<String, Object>) result).get("content"));
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }
}
