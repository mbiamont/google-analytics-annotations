package com.mbiamont.ga_annotations;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;
import java.util.Map;

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

    private boolean enabled = false;
    private String defaultTrackerId = null;
    private Context context = null;
    private boolean debugMode = false;

    private Map<String, Tracker> trackerMap = new HashMap<>();

    public void init(Context context, String defaultTrackerId) {
        this.context = context;
        this.defaultTrackerId = defaultTrackerId;
        enabled = true;
    }

    public void debugMode(Context context, boolean enabled) {
        debugMode = enabled;
        init(context, (defaultTrackerId != null) ? defaultTrackerId : "");
    }

    public boolean isEnabled() {
        return enabled && context != null;
    }

    private boolean check(String objectTracked, String trackerId) {
        if (context == null)
            Log.w("GA-Annotations", "Cannot track " + objectTracked + ". Call 'enable()' before.");
        else if (trackerId == null || trackerId.isEmpty())
            Log.w("GA-Annotations", "Cannot track " + objectTracked + ". Call 'init()' before or provide the trackerId.");
        return isEnabled();
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    private Tracker getTracker(String trackerId) {
        if (!trackerMap.containsKey(trackerId))
            trackerMap.put(trackerId, GoogleAnalytics.getInstance(context).newTracker(trackerId));
        return trackerMap.get(trackerId);
    }

    public void trackEvent(String trackerId, String category, String action, String label, long value) {
        if (trackerId == null || trackerId.isEmpty())
            trackerId = defaultTrackerId;

        if (check("event", trackerId))
            if (!debugMode)
                getTracker(trackerId).send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
            else
                Log.i("GA-Annotations", "trackEvent(" +
                        "\"" + trackerId + "\", " +
                        "\"" + category + "\", " +
                        "\"" + action + "\", " +
                        "\"" + label + "\", " +
                        value + ");");
    }

    public void trackEvent(String category, String action, String label, long value) {
        trackEvent(defaultTrackerId, category, action, label, value);
    }

    public void trackEvent(Event event) {
        trackEvent(event.getCategory(),
                event.getAction(),
                event.getLabel(),
                event.getValue());
    }

    public void trackScreen(String trackerId, String screenName) {
        if (trackerId == null || trackerId.isEmpty())
            trackerId = defaultTrackerId;

        if (check("screen", trackerId))
            if (!debugMode) {
                Tracker tracker = getTracker(trackerId);
                tracker.setScreenName(screenName);
                tracker.send(new HitBuilders.ScreenViewBuilder().build());
            } else
                Log.i("GA-Annotations", "trackScreen(" +
                        "\"" + trackerId + "\", " +
                        "\"" + screenName + "\");");
    }

    public void trackScreen(String screenName) {
        trackScreen(defaultTrackerId, screenName);
    }
}