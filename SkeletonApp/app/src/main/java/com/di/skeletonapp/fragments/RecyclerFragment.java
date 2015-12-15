package com.di.skeletonapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.adapters.RecyclerAdapter;

/**
 * Example of a "recycler" list. It is simply a list of items, but is optimized to only load
 * the items currently show (say, from the network).
 */
public class RecyclerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String... parameters) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, parameters[0]);
        args.putString(ARG_PARAM2, parameters[1]);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        // LinearLayoutManager makes the items appear as a vertical list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
