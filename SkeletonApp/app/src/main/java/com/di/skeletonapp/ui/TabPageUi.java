package com.di.skeletonapp.ui;

import android.view.View;
import android.widget.TextView;

import com.di.skeletonapp.R;
import com.di.skeletonapp.fragments.TabPageFragment;

public class TabPageUi {
    private final View mView;
    private final TabPageFragment mFragment;

    public TabPageUi(View view, TabPageFragment fragment) {
        mView = view;
        mFragment = fragment;
    }

    public void appendToTitle(String title) {
        TextView text = (TextView)mView.findViewById(R.id.tab_page_text);
        text.append(" (" + title + ")");
    }
}
