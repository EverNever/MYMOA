package com.uestc.mymoa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.MailQueryMailListHandler;
import com.uestc.mymoa.ui.MailItemContentActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class MailFragment extends Fragment {

    private ListView mailHistoryList;
    private MailQueryMailListHandler handler;
    private List<Map<String, Object>> mailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_mail_fragment, null);


        initLayout(view);
        initValues();
        initListener();
        return view;
    }

    private void initLayout(View view) {
        mailHistoryList = (ListView) view.findViewById(R.id.mailHistoryList);
    }

    private void initValues() {
        handler = new MailQueryMailListHandler();
        getData();
    }

    private void initListener() {
        mailHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MailItemContentActivity.class);
                intent.putExtra("mailid", String.valueOf(mailList.get(position).get("mailid")));
                startActivity(intent);
            }
        });
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("uid", Id.userId);

        handler.process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {
                mailList = result;
                mailHistoryList.setAdapter(new SimpleAdapter(getActivity(), mailList, R.layout.item_mail_history,
                        new String[]{"name", "time", "content"},
                        new int[]{R.id.mailUnameText, R.id.mailTimeText, R.id.mailContentText}));

            }

            @Override
            public void onSuccess(Object result) {
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getActivity(), "internet error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getNameById(){
        String name = "";

        

        return name;
    }

}
