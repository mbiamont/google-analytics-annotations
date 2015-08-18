package com.mbiamont.ga_annotations_sample;

import android.app.Application;

import com.mbiamont.ga_annotations.AnalyticsManager;

/**
 * Created by mbiamont on 04/08/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsManager.getInstance().debugMode(this, true);
        AnalyticsManager.getInstance().init(this, "YOUR_GOOGLE_ANALYTICS_TRACKER_ID");
    }
}
