package com.di.skeletonapp;

import android.os.Parcel;
import android.os.Parcelable;

public class FlowTracker implements Parcelable {

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

}
