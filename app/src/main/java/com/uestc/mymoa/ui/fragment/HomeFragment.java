package com.uestc.mymoa.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.uestc.mymoa.ParentLayoutOfChildViewPager;
import com.uestc.mymoa.R;
import com.uestc.mymoa.ui.adapter.NewsCategoryGridAdapter;
import com.uestc.mymoa.ui.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class HomeFragment extends Fragment {

    private static final int CHANGE_POST = 0x110;

    private GridView newsCategoryGrid;
    private ViewPager postViewPager;

    private ParentLayoutOfChildViewPager parentOfViewPagerLinear;

    private ImageView indicatorImage1;
    private ImageView indicatorImage2;
    private ImageView indicatorImage3;
    private ImageView indicatorImage4;

    private List<ImageView> listImage;
    private NewsCategoryGridAdapter newsCategoryGridAdapter;
    private PostAdapter postAdapter;
    private Timer timer;
    private TimerTask timerTask;

    private int currentPostId = -1;

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
    }

    private void initValues() {
        newsCategoryGridAdapter = new NewsCategoryGridAdapter(getActivity());
        newsCategoryGrid.setAdapter(newsCategoryGridAdapter);

        postAdapter = new PostAdapter(getActivity());
        postViewPager.setAdapter(postAdapter);

        parentOfViewPagerLinear.setChildViewPager(postViewPager);

        listImage = new ArrayList<>();
        listImage.add(indicatorImage1);
        listImage.add(indicatorImage2);
        listImage.add(indicatorImage3);
        listImage.add(indicatorImage4);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                currentPostId++;
                if (currentPostId == 4) {
                    currentPostId = 0;
                }
                postAutoChangeHandler.sendEmptyMessage(CHANGE_POST);

            }
        };

        timer.schedule(timerTask,0,2000);
    }

    private void initView() {
        listImage.get(0).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private void initListener() {
        postViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    listImage.get(i).setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                }
                listImage.get(position).setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private Handler postAutoChangeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CHANGE_POST:
                    postViewPager.setCurrentItem(currentPostId, true);
                    break;
            }
        }
    };
}
