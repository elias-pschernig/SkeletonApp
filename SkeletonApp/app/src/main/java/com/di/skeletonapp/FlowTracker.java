package com.di.skeletonapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This identifies one "screen" in the application flow. For us each screen also is a fragment
 * view. The screen is identified by its name and an array of parameters... in a real
 * application there should probably be one class for each screen derived from this one with
 * its proper parameters.
 * TODO: Is there a way to automatically parcelise?
 */
public class FlowTracker implements Parcelable {

    String uri;
    String[] parameters;

    // Either null if this is a root item, or else the parent screen.
    // TODO: need to make this parcelable so history can persist over a terminated app
    FlowTracker parent;

    private boolean mIsSibling;


    public static final Parcelable.Creator<FlowTracker> CREATOR
            = new Parcelable.Creator<FlowTracker>() {
        public FlowTracker createFromParcel(Parcel in) {
            return new FlowTracker(in);
        }

        public FlowTracker[] newArray(int size) {
            return new FlowTracker[size];
        }
    };

    FlowTracker(String uri, String[] parameters) {
        this.uri = uri;
        this.parameters = parameters;
    }

    FlowTracker(Parcel in) {
        this.uri = in.readString();
        this.parameters = in.createStringArray();
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

    public void setParent(FlowTracker parent) {
        this.parent = parent;
    }

    public FlowTracker getParent() {
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
}
