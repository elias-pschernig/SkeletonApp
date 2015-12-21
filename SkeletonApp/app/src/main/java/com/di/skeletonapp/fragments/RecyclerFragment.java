package com.di.skeletonapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.adapters.RecyclerAdapter;
import com.di.skeletonapp.framework.FlowFragment;

/**
 * Example of a "recycler" list. It is simply a list of items, but is optimized to only load
 * the items currently show (say, from the network).
 */
public class RecyclerFragment extends FlowFragment {


    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public RecyclerFragment() {
        // Required empty public constructor
    }


    public static RecyclerFragment newInstance(String... parameters) {
        RecyclerFragment fragment = new RecyclerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        Activity activity = getActivity();
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
