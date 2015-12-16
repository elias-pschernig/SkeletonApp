package com.di.skeletonapp;

import android.os.Parcel;
import android.os.Parcelable;

public class FlowTracker implements Parcelable {

    FlowTracker parent;
    String uri;
    String[] parameters;

    public static final Parcelable.Creator<FlowTracker> CREATOR
            = new Parcelable.Creator<FlowTracker>() {
        public FlowTracker createFromParcel(Parcel in) {
            return new FlowTracker(in);
        }

        public FlowTracker[] newArray(int size) {
            return new FlowTracker[size];
        }
    };

    private boolean mIsSibling;

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
