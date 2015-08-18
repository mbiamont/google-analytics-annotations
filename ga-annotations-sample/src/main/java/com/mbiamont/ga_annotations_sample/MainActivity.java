package com.mbiamont.ga_annotations_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mbiamont.ga_annotations.AnalyticsManager;
import com.mbiamont.ga_annotations.Event;
import com.mbiamont.ga_annotations.annotation.TrackEvent;
import com.mbiamont.ga_annotations.annotation.TrackScreen;

public class MainActivity extends AppCompatActivity {

    @TrackScreen(name = "MainActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @TrackEvent(category = "Method", action = "onCreateOptionMenu", label = "onCreateOptionsMenu(Menu menu)", value = 1)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
