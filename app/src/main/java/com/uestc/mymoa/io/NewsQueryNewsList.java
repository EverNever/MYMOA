package com.uestc.mymoa.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.io.model.NewsContent;
import com.uestc.mymoa.io.model.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class NewsQueryNewsList extends IOHandler {

    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Api.News.queryNewsList, params, new RequestCallBack<String>() {

            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = String.valueOf(responseInfo.result);
                if (result.indexOf("[") != -1) {
                    Type listType = new TypeToken<List<HashMap<String, Object>>>() {
                    }.getType();
                    Gson gson = new Gson();

                    List<HashMap<String, Object>> list =
                            gson.fromJson(responseInfo.result, listType);
                    callback.onSuccess(list);
                } else {
                    Gson gson = new Gson();
                    RequestStatus status = gson.fromJson(responseInfo.result, RequestStatus.class);
                    callback.onSuccess(status);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callback.onFailure(s);
            }
        });
    }
}
