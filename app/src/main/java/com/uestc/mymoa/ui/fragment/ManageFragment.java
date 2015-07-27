package com.uestc.mymoa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.mymoa.R;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class ManageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_manage_fragment, null);


        return view;
    }
}
