package com.uestc.mymoa.io;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.constant.Id;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class MailQueryMailListHandler extends IOHandler {
    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Api.Mail.queryMailList, params, new RequestCallBack<String>() {

            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type listType = new TypeToken<List<HashMap<String, Object>>>() {
                }.getType();
                Gson gson = new Gson();
                List<HashMap<String, Object>> list = gson.fromJson(responseInfo.result, listType);

                Log.i("run1", "--------------" + responseInfo.result);
                Log.i("run2", "--------------" + Id.userId);

                callback.onSuccess(list);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callback.onFailure(s);
            }
        });
    }
}
