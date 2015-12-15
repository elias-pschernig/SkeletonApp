package com.di.skeletonapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.StateParceler;

/**
 * Flow is very complex, this class has some of the required glue code.
 */
public class FlowActivity extends AppCompatActivity implements Flow.Dispatcher {

    private FlowDelegate mFlowDelegate;
    //private Flow mFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("flow", "onCreate");
        StateParceler parceler = new StateParceler() {
            @Override
            public Parcelable wrap(Object instance) {
                return null;
            }

            @Override
            public Object unwrap(Parcelable parcelable) {
                return null;
            }
        };

        History history = History.single(new FlowTracker("root", new String[]{""}));
        //mFlow = new Flow(history);
        //mFlow.setDispatcher(this);
        mFlowDelegate =
                FlowDelegate.onCreate(null, getIntent(), savedInstanceState, parceler,
                        history, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mFlowDelegate.onNewIntent(intent);
    }

    @Override
    public void onResume() {
        Log.d("flow", "onResume");
        super.onResume();
        mFlowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        mFlowDelegate.onPause();
        super.onPause();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mFlowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFlowDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        Log.d("flow", "onBackPressed");
        if (!mFlowDelegate.onBackPressed()) {
            // TODO: back pressed on last screen
            //super.onBackPressed();
        }
    }

    @Override
    public Object getSystemService(String name) {
        Log.d("flow", "getSystemService " + name);
        Object service = null;
        if (mFlowDelegate != null)
            service = mFlowDelegate.getSystemService(name);
        return service != null ? service : super.getSystemService(name);
    }

    public Fragment createFragment(FlowTracker position) {
        return null;
    }

    @Override
    public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {
        Log.d("flow", "dispatch " + traversal.destination);
        Log.d("flow", "traversal: " + traversal.origin + " - " + traversal.origin.size());
        FlowTracker tracker = (FlowTracker)traversal.destination.top();

        Fragment fragment = createFragment(tracker);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }
        callback.onTraversalCompleted();
    }

    public void setFlow(String uri, String... params) {
        // FIXME: is there another way?
        Flow flow = (Flow)mFlowDelegate.getSystemService("flow.Flow.FLOW_SERVICE");
        Log.d("flow", "set " + uri);
        Log.d("flow", "history: " + flow.getHistory());
        flow.set(new FlowTracker(uri, params));
    }
}
