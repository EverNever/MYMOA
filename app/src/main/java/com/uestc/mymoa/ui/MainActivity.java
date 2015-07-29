package com.uestc.mymoa.ui;

import android.content.Intent;
import android.graphics.Rect;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.BroadCastAction;
import com.uestc.mymoa.ui.adapter.MainFragmentPagerAdapter;
import com.uestc.mymoa.ui.fragment.ContactFragment;
import com.uestc.mymoa.ui.fragment.HomeFragment;
import com.uestc.mymoa.ui.fragment.ManageFragment;
import com.uestc.mymoa.ui.fragment.MailFragment;

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

    @ViewInject(R.id.homeImage)
    private ImageView homeImage;
    @ViewInject(R.id.messageImage)
    private ImageView messageImage;
    @ViewInject(R.id.contactImage)
    private ImageView contactImage;
    @ViewInject(R.id.manageIamge)
    private ImageView manageImage;


    @Override
    protected void initLayout() {
        ViewUtils.inject(this);
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
        fragmentList.add(new MailFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new ManageFragment());
        fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);

        viewpager.setAdapter(fragmentPagerAdapter);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        homeText.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_main_base;
    }

    private void createMenu() {
        View view = getLayoutInflater().inflate(R.layout.popupwindow_operation_menu_main, null);

        if (!isOperationMenuShowed) {

            operationMenu = new PopupWindow(view, getWindow().getDecorView().getWidth()*1 / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
            operationMenu.setAnimationStyle(R.style.popWindowAnimation);
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            operationMenu.showAtLocation(viewpager, Gravity.NO_GRAVITY, getWindow().getDecorView().getWidth()*15/32, toolbar.getHeight() + statusBarHeight);

            isOperationMenuShowed = true;

            ListView operationMainList = (ListView) view.findViewById(R.id.operationMainList);
            operationMainList.setAdapter(new SimpleAdapter(MainActivity.this, getOperations(), R.layout.item_operation_menu,
                    new String[]{"operation"}, new int[]{R.id.operationText}));

            operationMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(MainActivity.this, PostAddActivity.class));
                            break;
                        case 1:
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            startActivity(new Intent(MainActivity.this, MailSendNewActivity.class));
                            Toast.makeText(MainActivity.this, (CharSequence) getOperations().get(position).get("operation"), Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
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
        list.add("发送消息");

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
                homeImage.setImageResource(R.drawable.ic_main_home_normal);
                homeText.setTextColor(getResources().getColor(R.color.text_black_54));
                break;
            case CURRENT_IS_MESSAGE:
                messageImage.setImageResource(R.drawable.ic_main_mail_normal);
                messageText.setTextColor(getResources().getColor(R.color.text_black_54));
                break;
            case CURRENT_IS_CONTACT:
                contactImage.setImageResource(R.drawable.ic_main_contact_normal);
                contactText.setTextColor(getResources().getColor(R.color.text_black_54));
                break;
            case CURRENT_IS_MANAGE:
                manageImage.setImageResource(R.drawable.ic_main_manage_normal);
                manageText.setTextColor(getResources().getColor(R.color.text_black_54));
                break;
        }

        switch (currentPagerPosition) {
            case CURRENT_IS_HOME:
                homeImage.setImageResource(R.drawable.ic_main_home_focus);
                homeText.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case CURRENT_IS_MESSAGE:
                messageImage.setImageResource(R.drawable.ic_main_mail_focus);
                messageText.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case CURRENT_IS_CONTACT:
                contactImage.setImageResource(R.drawable.ic_main_contact_focus);
                contactText.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case CURRENT_IS_MANAGE:
                manageImage.setImageResource(R.drawable.ic_main_manage_focus);
                manageText.setTextColor(getResources().getColor(R.color.colorAccent));
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
            Intent intent = new Intent();
            intent.setAction(BroadCastAction.ACTION_FINISH);
            sendBroadcast(intent);
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
