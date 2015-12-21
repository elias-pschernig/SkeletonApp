package com.di.skeletonapp.ui;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.di.skeletonapp.R;
import com.di.skeletonapp.adapters.RecyclerAdapter;
import com.di.skeletonapp.fragments.RecyclerFragment;

public class RecyclerUi {
    private final View mView;
    private final RecyclerFragment mFragment;

    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public RecyclerUi(View view, RecyclerFragment fragment) {
        mView = view;
        mFragment = fragment;

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        Activity activity = mFragment.getActivity();
        // TODO: how can this happen and what should we do
        if (activity == null)
            return;
        // LinearLayoutManager makes the items appear as a vertical list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        // our adapter which takes care of managing the items in the list as well of presenting them
        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // as an example, add some items
        for (int i = 0; i < 24; i++) {
            mAdapter.addItem("recycler item " + i);
        }

        // add a SwipeRefreshLayout, this detects a swipe at the top to refresh the view
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.emptyList_SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.addItem("refreshed item " + mAdapter.getItemCount());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
