package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.framework.FlowStack;
import com.di.skeletonapp.framework.FlowActivity;
import com.di.skeletonapp.framework.FlowFragment;
import com.di.skeletonapp.model.Detail;
import com.di.skeletonapp.ui.DetailUi;

import icepick.State;

/**
 * Fragment showing some detail for an item, with a way to show details of another item.
 * This is here to demonstrate a chain of fragments you typically would want back navigation
 * for.
 */
public class DetailFragment extends FlowFragment {
    @State
    Detail mDetail;
    DetailUi mUi;

    public static DetailFragment newInstance(Detail model) {
        DetailFragment fragment = new DetailFragment();
        fragment.mDetail = model;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        mUi = new DetailUi(view, this);

        mUi.appendValue("\nParameters: " + mDetail.getLetter() + ", " + mDetail.getNumber());

        return view;
    }

    public void onLetterButton() {
        Detail newDetail = new Detail(mDetail.getNextLetter(), mDetail.getNumber());
        FlowStack.setBackstackUp((FlowActivity) getActivity(), DetailFragment.newInstance(newDetail));
    }

    public void onNumberButton() {
        Detail newDetail = new Detail(mDetail.getLetter(), mDetail.getNextNumber());
        FlowStack.setBackstackBack((FlowActivity) getActivity(), DetailFragment.newInstance(newDetail));
    }
}
