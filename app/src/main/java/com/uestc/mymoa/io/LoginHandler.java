package com.uestc.mymoa.io;

import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.io.model.RequestStatus;

/**
 * Created by chao on 2015/7/27.
 */
public class LoginHandler extends IOHandler {

    protected IOCallback<RequestStatus> callback;

    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Users.login,
                params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.e("my","resultJson--"+responseInfo.result);
                        Gson gson = new Gson();
                        RequestStatus status = gson.fromJson(responseInfo.result, RequestStatus.class);
                        callback.onSuccess(status);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        callback.onFailure(msg);
                    }
                });
    }
}
