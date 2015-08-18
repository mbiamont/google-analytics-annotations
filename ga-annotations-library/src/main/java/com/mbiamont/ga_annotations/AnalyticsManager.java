package com.mbiamont.ga_annotations;

import android.content.Context;
import android.util.Log;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

/**
 * Created by mbiamont on 04/08/15.
 */
public final class AnalyticsManager {
    private static AnalyticsManager ourInstance = new AnalyticsManager();

    public static AnalyticsManager getInstance() {
        return ourInstance;
    }

    private AnalyticsManager() {
    }

    private boolean enabled   = false;
    private String  trackerId = null;
    private Context context   = null;
    private boolean debugMode = false;

    public void init(Context context, String trackerId) {
        this.context = context;
        this.trackerId = trackerId;
        enabled = true;
    }

    public void debugMode(Context context, boolean enabled) {
        debugMode = enabled;
        init(context, (trackerId != null) ? trackerId : "");
    }

    public boolean isEnabled() {
        return enabled && trackerId != null && context != null;
    }

    private boolean check(String objectTracked) {
        if (isEnabled())
            return true;
        else if (context != null && trackerId != null)
            Log.w("GA-Annotations", "Cannot track " + objectTracked + ". Call 'enable()' before.");
        else
            Log.w("GA-Annotations", "Cannot track " + objectTracked + ". Call 'init()' before.");
        return false;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void trackEvent(String category, String action, String label, long value) {
        if (check("event"))
            if (!debugMode)
                GoogleAnalytics
                        .getInstance(context)
                        .getTracker(trackerId)
                        .send(MapBuilder.createEvent(category,
                                action,
                                label,
                                value).build());
            else
                Log.i("GA-Annotations", "trackEvent(" +
                        "\"" + category + "\", " +
                        "\"" + action + "\", " +
                        "\"" + label + "\", " +
                        value + ");");

    }

    public void trackEvent(Event event) {
        trackEvent(event.getCategory(),
                event.getAction(),
                event.getLabel(),
                event.getValue());
    }

    public void trackScreen(String screenName) {
        if (check("screen"))
            if (!debugMode) {
                Tracker tracker = GoogleAnalytics.getInstance(context).getTracker(trackerId);
                tracker.set(Fields.SCREEN_NAME, screenName);
                tracker.send(MapBuilder.createAppView().build());
            } else
                Log.i("GA-Annotations", "trackScreen(" +
                        "\"" + screenName + "\");");
    }
}