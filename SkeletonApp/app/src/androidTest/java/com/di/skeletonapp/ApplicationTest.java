package com.di.skeletonapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import flow.Flow;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application>
implements Flow.Dispatcher {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {

    }
}