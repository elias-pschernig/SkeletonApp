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
        if (!mFlowDelegate.onBackPressed()) {
            // TODO: back pressed on last screen
            //super.onBackPressed();
        }
    }

    public void onUpPressed() {
        Flow flow = (Flow)mFlowDelegate.getSystemService("flow.Flow.FLOW_SERVICE");
        FlowTracker current = flow.getHistory().top();
        if (current.getParent() != null) {
            flow.set(current.getParent());
        }
        else {
            onBackPressed();
        }
    }

    @Override
    public Object getSystemService(String name) {
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

    public void setFlow(String uri, String[] params, boolean canGoBack, boolean goUp) {
        Flow flow = getFlow();

        History history = flow.getHistory();
        FlowTracker current = history.top();

        Log.d("flow", "set " + uri);
        Log.d("flow", "history: " + history);
        FlowTracker tracker = new FlowTracker(uri, params);
        if (!canGoBack) {
            flow.setHistory(History.single(tracker), Flow.Direction.REPLACE);
        }
        else {
            if (goUp) {
                tracker.setParent(current);
            }
            else {
                tracker.setParent(current.getParent());
                tracker.setIsSibling(true);
            }
        }
        flow.set(tracker);
    }

    public void setFlowRoot(String uri, String... params) {
        setFlow(uri, params, false, false);
    }

    public void setFlowUp(String uri, String... params) {
        setFlow(uri, params, true, true);
    }

    public void setFlowBack(String uri, String... params) {
        setFlow(uri, params, true, false);
    }

    String getBackStackDescription() {
        String s = "";
        for (Object o : getFlow().getHistory()) {
            FlowTracker t = (FlowTracker)o;
            String s2 = t.toString();
            if (t.isSibling()) {
                s2 = " -> " + s2;
            }
            else {
                if (t.getParent() != null)
                    s2 = " / " + s2;
            }
            s = s2 + s;
        }
        return s;
    }

    Flow getFlow() {
        // FIXME: is there another way?
        Flow flow = (Flow) mFlowDelegate.getSystemService("flow.Flow.FLOW_SERVICE");
        //Flow flow = Flow.get(getApplicationContext());
        return flow;
    }
}
