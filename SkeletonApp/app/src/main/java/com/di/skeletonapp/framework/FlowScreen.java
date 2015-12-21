package com.di.skeletonapp.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * This identifies one "screen" in the application flow. For us each screen also is a fragment
 * view. The screen is identified by its name and an array of parameters... in a real
 * application there should probably be one class for each screen derived from this one with
 * its proper parameters.
 * TODO: Is there a way to automatically parcelise?
 */
public class FlowScreen implements Parcelable {

    public String uri;
    public String[] parameters;

    // Either null if this is a root item, or else the parent screen.
    // TODO: need to make this parcelable so history can persist over a terminated app
    FlowScreen parent;

    private boolean mIsSibling;

    // TODO: I don't like this but the activity is needed and couldn't figure out a better
    // way to obtain a reference to it so far.
    private static transient FlowActivity mActivity;


    public static final Parcelable.Creator<FlowScreen> CREATOR
            = new Parcelable.Creator<FlowScreen>() {
        public FlowScreen createFromParcel(Parcel in) {
            return new FlowScreen(in);
        }

        public FlowScreen[] newArray(int size) {
            return new FlowScreen[size];
        }
    };

    FlowScreen(String uri, String[] parameters) {
        this.uri = uri;
        this.parameters = parameters;
        Log.d("FlowTracker", "storing " + this.toString());
    }

    FlowScreen(Parcel in) {
        this.uri = in.readString();
        this.parameters = in.createStringArray();
        Log.d("FlowTracker", "restoring " + this.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uri);
        dest.writeStringArray(parameters);
    }

    public void setParent(FlowScreen parent) {
        this.parent = parent;
    }

    public FlowScreen getParent() {
        return parent;
    }

    @Override
    public String toString() {
        String s = this.uri + "(";
        for (String p : parameters) {
            if (!s.endsWith("("))
                s += ", ";
            s += p;
        }
        s += ")";
        return s;
    }

    public void setIsSibling(boolean isSibling) {
        mIsSibling = isSibling;
    }

    public boolean isSibling() {
        return mIsSibling;
    }

    public static void connectActivity(FlowActivity activity) {
        mActivity = activity;
    }

    public static void disconnectActivity() {
        mActivity = null;
    }

    public void activate() {
        if (mActivity == null)
            return;
        mActivity.showFragment(this);
    }
}
