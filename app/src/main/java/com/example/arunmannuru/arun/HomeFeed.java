package com.example.arunmannuru.arun;

import android.animation.Animator;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ArunMannuru on 4/16/17.
 */

public class HomeFeed extends ListFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
public HomeFeed(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_feed, container, false);

        return view;
    }


}
