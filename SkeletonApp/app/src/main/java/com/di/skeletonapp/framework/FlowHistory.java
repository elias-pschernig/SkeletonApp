package com.di.skeletonapp.framework;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A very simple backstack implementation. It calls the "activate" method of the Screen at the
 * top of the stack whenever the contents change.
 *
 * It also has the ability to save and restore itself from the application bundle by implementing
 * the Parcelable interface.
 */
public class FlowHistory implements Serializable {
    private Deque<FlowScreen> mDeque = new ArrayDeque<FlowScreen>();

    private void activateCurrent() {
        FlowScreen current = getCurrent();
        if (current != null) {
            current.activate();
        }
    }

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
