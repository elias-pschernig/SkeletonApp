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
public class BackStack<T extends Screen> implements Parcelable {
    private Deque<T> mDeque = new ArrayDeque<T>();

    protected BackStack(Parcel in) {
        if (in != null) {
            int n = in.readInt();
            for (int i = 0; i < n; i++) {
                mDeque.addLast((T) in.readParcelable(null));
            }
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDeque.size());
        for (T screen : mDeque) {
            screen.writeToParcel(dest, flags);
        }
    }

    static public BackStack<?> onCreate(Bundle savedInstance) {
        if (savedInstance != null) {
            BackStack<?> backstack = savedInstance.getParcelable("BackStack");
            backstack.activateCurrent();
            return backstack;
        }
        return new BackStack<>(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("BackStack", this);
    }


    private void activateCurrent() {
        T current = getCurrent();
        if (current != null) {
            current.activate();
        }
    }

    public static final Creator<BackStack> CREATOR = new Creator<BackStack>() {
        @Override
        public BackStack createFromParcel(Parcel in) {
            return new BackStack(in);
        }

        @Override
        public BackStack[] newArray(int size) {
            return new BackStack[size];
        }
    };

    /**
     * Add a new screen to the back stack.
     * @param screen
     */
    public void add(T screen) {
        mDeque.addLast(screen);
        screen.activate();
    }

    /**
     * Return to a previous position in the back stack.
     * @param screen
     * @return
     */
    public boolean goUpTo(T screen) {
        if (!mDeque.contains(screen))
            return false;
        while (!mDeque.isEmpty()) {
            T last = mDeque.getLast();
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

    public T getCurrent() {
        return mDeque.peekLast();
    }

    public Iterable<? extends Object> getHistory() {
        return mDeque;
    }

    public void clear() {
        mDeque.clear();
    }

}
