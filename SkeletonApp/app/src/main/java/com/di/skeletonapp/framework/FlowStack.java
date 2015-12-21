package com.di.skeletonapp.framework;

public class FlowStack {
    public static void setBackstackUp(FlowActivity activity, FlowFragment fragment) {
        if (activity == null) return;
        activity.setFlow(fragment, true, true);
    }

    public static void setBackstackBack(FlowActivity activity, FlowFragment fragment) {
        if (activity == null) return;
        activity.setFlow(fragment, true, false);
    }

    public static void setBackstackRoot(FlowActivity activity, FlowFragment fragment) {
        if (activity == null) return;
        activity.setFlow(fragment, false, false);
    }

}
