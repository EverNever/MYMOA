package com.uestc.mymoa.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.mymoa.ui.fragment.ContactFragment;
import com.uestc.mymoa.ui.fragment.HomeFragment;
import com.uestc.mymoa.ui.adapter.MainFragmentPagerAdapter;
import com.uestc.mymoa.ui.fragment.ManageFragment;
import com.uestc.mymoa.ui.fragment.MessageFragment;
import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity {

    private static final int CURRENT_IS_HOME = 0;
    private static final int CURRENT_IS_MESSAGE = 1;
    private static final int CURRENT_IS_CONTACT = 2;
    private static final int CURRENT_IS_MANAGE = 3;

    //状态变量
    private int previousPagerPosition = CURRENT_IS_HOME;
    private int currentPagerPosition = CURRENT_IS_HOME;
    private boolean isOperationMenuShowed = false;

    private LinearLayout homeLinear;
    private LinearLayout messageLinear;
    private LinearLayout contactLinear;
    private LinearLayout manageLinear;

    private TextView homeText;
    private TextView messageText;
    private TextView contactText;
    private TextView manageText;

    private PopupWindow operationMenu;

    private ViewPager viewpager;
    private Toolbar toolbar;

    private MainFragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void initLayout() {
        homeLinear = (LinearLayout) findViewById(R.id.homeLinear);
        messageLinear = (LinearLayout) findViewById(R.id.messageLinear);
        contactLinear = (LinearLayout) findViewById(R.id.contactLinear);
        manageLinear = (LinearLayout) findViewById(R.id.manageLinear);

        homeText = (TextView) findViewById(R.id.homeText);
        messageText = (TextView) findViewById(R.id.messageText);
        contactText = (TextView) findViewById(R.id.contactText);
        manageText = (TextView) findViewById(R.id.manageText);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    protected void initListener() {
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("position", "" + position);
                if (position != currentPagerPosition) {
                    currentPagerPosition = position;
                    setBottomTextColor(previousPagerPosition, currentPagerPosition);
                    previousPagerPosition = currentPagerPosition;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                viewpager.setCurrentItem(CURRENT_IS_HOME, true);
            }
        });

        messageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                viewpager.setCurrentItem(CURRENT_IS_MESSAGE, true);
            }
        });

        contactLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "contact", Toast.LENGTH_SHORT).show();
                viewpager.setCurrentItem(CURRENT_IS_CONTACT, true);
            }
        });

        manageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "manage", Toast.LENGTH_SHORT).show();
                viewpager.setCurrentItem(CURRENT_IS_MANAGE, true);
            }
        });
    }

    @Override
    protected void initValue() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new ManageFragment());
        fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);

        viewpager.setAdapter(fragmentPagerAdapter);

        toolbar.setTitle("Main");
        setSupportActionBar(toolbar);

        homeText.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_main_base;
    }

    private void createMenu() {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_operation_menu_main, null);

        if (!isOperationMenuShowed) {

            operationMenu = new PopupWindow(view, getWindow().getDecorView().getWidth() / 3, ViewGroup.LayoutParams.WRAP_CONTENT);
            operationMenu.setAnimationStyle(R.style.popWindowAnimation);
            operationMenu.showAsDropDown(toolbar, 0, 0, Gravity.RIGHT);
            isOperationMenuShowed = true;


            ListView operationMainList = (ListView) view.findViewById(R.id.operationMainList);

            operationMainList.setAdapter(new SimpleAdapter(MainActivity.this, getOperations(), R.layout.item_operation_menu,
                    new String[]{"operation"}, new int[]{R.id.operationText}));


            operationMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    operationMenu.dismiss();
                    isOperationMenuShowed = false;
                }
            });

        }

    }

    private List<Map<String, Object>> getOperations() {
        List<String> list = new ArrayList<String>();
        List<Map<String, Object>> list1 = new ArrayList<>();

        list.add("发布公告");
        list.add("发布新闻");
        list.add("新建内部短信");
        list.add("新建手机短信");
        list.add("新建联系人");

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("operation", list.get(i));

            list1.add(map);
        }
        return list1;
    }

    private void setBottomTextColor(int previousPagerPosition, int currentPagerPosition) {
        switch (previousPagerPosition) {
            case CURRENT_IS_HOME:
                homeText.setTextColor(getResources().getColor(R.color.white));
                break;
            case CURRENT_IS_MESSAGE:
                messageText.setTextColor(getResources().getColor(R.color.white));
                break;
            case CURRENT_IS_CONTACT:
                contactText.setTextColor(getResources().getColor(R.color.white));
                break;
            case CURRENT_IS_MANAGE:
                manageText.setTextColor(getResources().getColor(R.color.white));
                break;
        }

        switch (currentPagerPosition) {
            case CURRENT_IS_HOME:
                homeText.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case CURRENT_IS_MESSAGE:
                messageText.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case CURRENT_IS_CONTACT:
                contactText.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case CURRENT_IS_MANAGE:
                manageText.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_index, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            createMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isOperationMenuShowed) {
            operationMenu.dismiss();
            isOperationMenuShowed = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isOperationMenuShowed) {
            operationMenu.dismiss();
            isOperationMenuShowed = false;
            return isOperationMenuShowed;
        } else {

            return super.dispatchTouchEvent(ev);
        }
    }

}
