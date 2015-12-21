package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.framework.FlowFragment;
import com.di.skeletonapp.ui.RecyclerUi;

/**
 * Example of a "recycler" list. It is simply a list of items, but is optimized to only load
 * the items currently show (say, from the network).
 */
public class RecyclerFragment extends FlowFragment {

    private RecyclerUi mUi;

    public static RecyclerFragment newInstance(String... parameters) {
        return new RecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recycler, container, false);
        mUi = new RecyclerUi(view, this);
        return view;
    }



}
