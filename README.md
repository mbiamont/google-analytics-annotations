#Google Analytics Annotations

Android library to use Google Analytics through annotations.

# Installation

TODO

# Usage

### Initialization
In the `onCreate()` method of your `Application` class, add this line :
```java
  AnalyticsManager.getInstance().init(context, "YOUR_GOOGLE_ANALYTICS_TRACKER_ID");
```

### Track event
```java
@TrackEvent(category = "ui_action", action = "on_click", label = "on_click_login_button", value = 1)
private void onLoginButtonClicked(){
  (...)
}
```

### Track screen

```java
@TrackScreen(name = "MainActivity")
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  (...)
}
```

# More

### DebugMode
If you just want to check if the tracks are correctly implemented; you can use the debugMode. It'll print tracks in LogCat instead of send them.
So, instead of call `AnalyticsManager.getInstance().init()`, call `AnalyticsManager.getInstance().debugMode(context, true);`

### Manual track
Sometimes, you cannot use annotations (if your tracks require a condition). In this case, you can use trackers manually :
```java
private void onLoginButtonClicked(){
  if(user.isLogged()){
    AnalyticsManager.getInstance().trackEvent(new Event.Builder()
            .withCategory("ui_action")
            .withAction("on_click")
            .withLabel("on_click_login_button")
            .build());
    //or simply : AnalyticsManager.getInstance().trackEvent("ui_action", "on_click", "on_click_login_button", 1);
  }
  (...)
}
```

or

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  if(user.isLogged()){
    AnalyticsManager.getInstance().trackScreen("MainActivity");
  }
  (...)
}
```

# Roadmap

- Implement a 'list' system to send tracks on multiple Google Analytics accounts.
