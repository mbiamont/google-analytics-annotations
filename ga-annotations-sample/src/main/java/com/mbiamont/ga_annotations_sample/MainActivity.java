package com.mbiamont.ga_annotations_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ga_annotations_sample_library.MainController;
import com.mbiamont.ga_annotations.AnalyticsManager;
import com.mbiamont.ga_annotations.annotation.TrackEvent;
import com.mbiamont.ga_annotations.annotation.TrackScreen;

public class MainActivity extends AppCompatActivity {

    @TrackScreen(trackerId = "TRACKERID1", name = "MainActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClicked();
            }
        });
    }

    @TrackEvent(trackerId = "TRACKERID2", category = "UI", action = "onCreateOptionMenu", label = "MainActivity", value = 1)
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

    private void onLoginButtonClicked() {
        AnalyticsManager.getInstance().trackEvent("TRACKERID3", "ui_action", "on_click", "on_click_login_button", 1);
        doStuff();
    }

    private void doStuff() {
        MainController.doStuff();
    }
}
