package com.cmbb.smartkids.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseFragment;

/**
 * Created by N.Sun
 */
public class FragmentToolsPractical extends BaseFragment {
    private static final String TAG = FragmentToolsPractical.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tools_practical, container, false);
        return rootView;
    }
}
