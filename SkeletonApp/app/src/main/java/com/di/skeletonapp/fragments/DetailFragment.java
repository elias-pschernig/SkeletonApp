package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.framework.FlowFragment;

/**
 * Fragment showing some detail for an item, with a way to show details of another item.
 * This is here to demonstrate a chain of fragments you typically would want back navigation
 * for.
 */
public class DetailFragment extends FlowFragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param parameters Parameters for this fragment.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String... parameters) {
        DetailFragment fragment = new DetailFragment();

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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);



        return view;
    }

}
