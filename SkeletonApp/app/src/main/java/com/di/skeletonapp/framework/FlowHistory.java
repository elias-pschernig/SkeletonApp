package com.di.skeletonapp.framework;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A very simple backstack implementation. It calls the "activate" method of the Screen at the
 * top of the stack whenever the contents change.
 *
 * It also has the ability to save and restore itself from the application bundle by implementing
 * the Parcelable interface.
 */
public class FlowHistory implements Parcelable {
    private Deque<FlowScreen> mDeque = new ArrayDeque<FlowScreen>();

    protected FlowHistory(Parcel in) {
        if (in != null) {
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                mDeque.addLast((FlowScreen) in.readParcelable(null));
            }
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDeque.size());
        for (FlowScreen screen : mDeque) {
            screen.writeToParcel(dest, flags);
        }
    }

    static public FlowHistory onCreate(Bundle savedInstance) {
        if (savedInstance != null) {
            FlowHistory backstack = savedInstance.getParcelable("BackStack");
            backstack.activateCurrent();
            return backstack;
        }
        return new FlowHistory(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("BackStack", this);
    }


    private void activateCurrent() {
        FlowScreen current = getCurrent();
        if (current != null) {
            current.activate();
        }
    }

    public static final Creator<FlowHistory> CREATOR = new Creator<FlowHistory>() {
        @Override
        public FlowHistory createFromParcel(Parcel in) {
            return new FlowHistory(in);
        }

        @Override
        public FlowHistory[] newArray(int size) {
            return new FlowHistory[size];
        }
    };

    /**
     * Add a new screen to the back stack.
     * @param screen
     */
    public void add(FlowScreen screen) {
        mDeque.addLast(screen);
        screen.activate();
    }

    /**
     * Return to a previous position in the back stack.
     * @param screen
     * @return
     */
    public boolean goUpTo(FlowScreen screen) {
        if (!mDeque.contains(screen))
            return false;
        while (!mDeque.isEmpty()) {
            FlowScreen last = mDeque.getLast();
            if (last == screen) {
                screen.activate();
                return true;
            }
            mDeque.removeLast();
        }
        return false;
    }

    /**
     * Go back one in the back stack.
     * @return
     */
    public boolean goBack() {
        if (mDeque.isEmpty()) return false;
        mDeque.removeLast();
        if (mDeque.isEmpty()) return false;
        mDeque.getLast().activate();
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public FlowScreen getCurrent() {
        return mDeque.peekLast();
    }

    public Iterable<? extends Object> getHistory() {
        return mDeque;
    }

    public void clear() {
        mDeque.clear();
    }

}
