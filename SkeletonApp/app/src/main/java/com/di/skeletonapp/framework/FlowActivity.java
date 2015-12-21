package com.di.skeletonapp.framework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Flow is very complex, this class has some of the required glue code.
 */
public abstract class FlowActivity extends AppCompatActivity {
    BackStack<FlowTracker> mBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlowTracker.connectActivity(this);
        mBackStack = (BackStack<FlowTracker>) BackStack.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        FlowTracker.connectActivity(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        FlowTracker.disconnectActivity();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBackStack.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (!mBackStack.goBack()) {
            // TODO: back pressed on last screen
            //super.onBackPressed();
        }
    }

    public void onUpPressed() {
        FlowTracker current = mBackStack.getCurrent();
        if (current.getParent() != null) {
            mBackStack.goUpTo(current.getParent());
        }
        else {
            onBackPressed();
        }
    }

    public abstract void showFragment(FlowTracker position);

    public void setFlow(String uri, String[] params, boolean canGoBack, boolean goUp) {
        FlowTracker current = mBackStack.getCurrent();

        FlowTracker tracker = new FlowTracker(uri, params);
        if (!canGoBack) {
            mBackStack.clear();
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
        mBackStack.add(tracker);
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

    public String getBackStackDescription() {
        String s = "";
        for (Object o : mBackStack.getHistory()) {
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

}
