package com.di.skeletonapp.framework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import icepick.Icepick;
import icepick.State;

/**
 * Base class for the main activity which handles some of the gory details.
 */
public abstract class FlowActivity extends AppCompatActivity {
    @State
    FlowHistory mFlowHistory;

    /**
     * What we do here is provide the activity instance to the FlowScreen class and make sure
     * to restore mFlowHistory from the saved instance (or recreate on first run).
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlowScreen.connectActivity(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        if (mFlowHistory == null) {
            mFlowHistory = new FlowHistory();
        }
    }

    /**
     * Store (via icepick) the activity state.
     */
    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        FlowScreen.connectActivity(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        FlowScreen.disconnectActivity();
    }

    @Override
    public void onBackPressed() {
        if (!mFlowHistory.goBack()) {
            // TODO: back pressed on last screen
            //super.onBackPressed();
        }
    }

    public void onUpPressed() {
        FlowScreen current = mFlowHistory.getCurrent();
        if (current.getParent() != null) {
            mFlowHistory.goUpTo(current.getParent());
        }
        else {
            onBackPressed();
        }
    }

    public abstract void showCurrentFragment();

    public void setFlow(FlowFragment fragment, boolean canGoBack, boolean goUp) {
        FlowScreen current = mFlowHistory.getCurrent();

        FlowScreen tracker = new FlowScreen(fragment);
        if (!canGoBack) {
            mFlowHistory.clear();
        }
        else {
            if (goUp) {
                tracker.setParent(current);
            }
            else {
                if (current != null)
                    tracker.setParent(current.getParent());
            }
        }
        mFlowHistory.add(tracker);
    }

    public FlowHistory getHistory() {
        return mFlowHistory;
    }

    protected String getBackStackDescription() {
        String s = "";
        FlowScreen prev = null;
        for (Object o : mFlowHistory.getHistory()) {
            FlowScreen t = (FlowScreen)o;
            String s2 = t.toString();
            FlowScreen parent = t.getParent();
            if (parent != prev) {
                s2 = " -> " + s2;
            }
            else {
                if (t.getParent() != null)
                    s2 = " / " + s2;
            }
            prev = parent;
            s = s + s2;
        }
        return s;
    }

}
