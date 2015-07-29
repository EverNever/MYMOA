package com.uestc.mymoa.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.uestc.mymoa.common.view.ParentLayoutOfChildViewPager;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.PostQueryPostListHandler;
import com.uestc.mymoa.io.model.RequestStatus;
import com.uestc.mymoa.ui.NewsListActivity;
import com.uestc.mymoa.ui.adapter.NewsCategoryGridAdapter;
import com.uestc.mymoa.ui.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class HomeFragment extends Fragment {
    private PostQueryPostListHandler handler;

    private Context context;

    private static final int CHANGE_POST = 0x110;

    public static final int NEWS_LOCAL = 0;
    public static final int NEWS_ENTERTAINMENT = 1;
    public static final int NEWS_SCIENCE_AND_TEchnology = 2;
    public static final int NEWS_BRIGHT_YELLOW = 3;

    private GridView newsCategoryGrid;
    private ViewPager postViewPager;

    private ParentLayoutOfChildViewPager parentOfViewPagerLinear;

    private ImageView indicatorImage1;
    private ImageView indicatorImage2;
    private ImageView indicatorImage3;
    private ImageView indicatorImage4;
    private ImageView indicatorImage5;

    private List<ImageView> listImage;
    private NewsCategoryGridAdapter newsCategoryGridAdapter;
    private PostAdapter postAdapter;
    private Timer timer;
    private TimerTask timerTask;

    private int currentPostId = -1;

    private boolean isTimerRunning = false;

    private List<Map<String, Object>> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_home_fragment, null);

        initLayout(view);
        initValues();
        initView();
        initListener();

        return view;
    }

    private void initLayout(View view) {
        postViewPager = (ViewPager) view.findViewById(R.id.postViewPager);
        parentOfViewPagerLinear = (ParentLayoutOfChildViewPager)
                view.findViewById(R.id.parentOfViewPagerLinear);
        newsCategoryGrid = (GridView) view.findViewById(R.id.newsCategoryGrid);

        indicatorImage1 = (ImageView) view.findViewById(R.id.indicatorImage1);
        indicatorImage2 = (ImageView) view.findViewById(R.id.indicatorImage2);
        indicatorImage3 = (ImageView) view.findViewById(R.id.indicatorImage3);
        indicatorImage4 = (ImageView) view.findViewById(R.id.indicatorImage4);
        indicatorImage5 = (ImageView) view.findViewById(R.id.indicatorImage5);
    }

    private void initValues() {
        handler = new PostQueryPostListHandler();

        newsCategoryGridAdapter = new NewsCategoryGridAdapter(getActivity());
        newsCategoryGrid.setAdapter(newsCategoryGridAdapter);


        parentOfViewPagerLinear.setChildViewPager(postViewPager);

        listImage = new ArrayList<>();
        listImage.add(indicatorImage1);
        listImage.add(indicatorImage2);
        listImage.add(indicatorImage3);
        listImage.add(indicatorImage4);
        listImage.add(indicatorImage5);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                currentPostId++;
                if (currentPostId == 5) {
                    currentPostId = 0;
                }
                postAutoChangeHandler.sendEmptyMessage(CHANGE_POST);

            }
        };
        if (!isTimerRunning) {
            isTimerRunning = true;
            timer.schedule(timerTask, 0, 4000);
        }
    }

    private void initView() {
        getPostList();
    }

    private void initListener() {
        postViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 5; i++) {
                    listImage.get(i).setBackgroundResource(R.color.whiteAlpha);
                }
                listImage.get(position).setBackgroundResource(R.color.whiteDark);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        newsCategoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NewsListActivity.class);
                intent.putExtra("news_category", position + 1);
                startActivity(intent);
            }
        });
    }

    private Handler postAutoChangeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_POST:
                    postViewPager.setCurrentItem(currentPostId, true);
                    break;
            }
        }
    };

    private void getPostList(){
        handler.process(null, new IOCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List result) {
                postAdapter = new PostAdapter(getActivity(),result);
                postViewPager.setAdapter(postAdapter);
            }

            @Override
            public void onSuccess(Object result) {
                if(((RequestStatus)result).code==1){
                    Toast.makeText(HomeFragment.this.getActivity(), "获取出现未知错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(HomeFragment.this.getActivity(), "网络错误,请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
