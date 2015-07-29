package com.uestc.mymoa.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.view.InputDialog;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class ContactGroupListActivity extends BaseActivity {

    private ListView groupList;
    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    private void getGroupList(){

        new ContactHandler().getGroupList(new IOCallback<HashMap<String, Object>>() {
            @Override
            public void onStart() {
                //TODO
            }

            @Override
            public void onSuccess(List<HashMap<String, Object>> result) {

                list = result;
                refreshAdapter();
            }

            @Override
            public void onSuccess(HashMap<String, Object> result) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
    private void addGroup(String groupname){

        RequestParams params = new RequestParams();

        params.addBodyParameter("groupname", groupname);

        new ContactHandler().addGroup(params, new IOCallback<RequestStatus>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List<RequestStatus> result) {

            }

            @Override
            public void onSuccess(RequestStatus result) {
                if (result.code == 200) {
                    Toast.makeText(ContactGroupListActivity.this, "添加联系人群组成功",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactGroupListActivity.this, "添加联系人群组失败",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    private void refreshAdapter(){
        groupList.setAdapter(new SimpleAdapter(ContactGroupListActivity.this,
                list, R.layout.item_contact,
                new String[]{"groupname"}, new int[]{R.id.itemText}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groupList = (ListView) findViewById(R.id.groupList);

        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ContactGroupListActivity.this,
                        ContactGroupDetailActivity.class);

                String str = list.get(position)
                        .get("groupid").toString();
                intent.putExtra("groupid", str.substring(0, str.lastIndexOf(".")));
                startActivity(intent);
            }
        });
        list.clear();
        getGroupList();
    }

    @Override
    protected void initLayout() {
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_mygroup;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_add, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            final InputDialog inputDialog = new InputDialog(ContactGroupListActivity.this);
            inputDialog.setInputEditHint("请输入群组名称");
            inputDialog.buider
                    .setTitle("新建联系人群组")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            addGroup(inputDialog.getInputEditText());
                            list.clear();
                            getGroupList();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
            getGroupList();
            refreshAdapter();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

