#Google Analytics Annotations

## Introduction

Are you not tired of writing code to track a simple event/screen ? This library allows you to do the same job in a single line using annotations !

### TrackEvent annotation :

```java
@TrackEvent(category = "category", action = "action", label = "label", value = 1)
```

### TrackScreen annotation :

```java
@TrackScreen(name = "name")
```

## Installation

TODO

## Usage

### Initialization
The first thing to do is to initialize the `AnalyticsManager`.The best way is to do it in your `Application`.

To do that :

```java
  AnalyticsManager.getInstance().init(context, "YOUR_GOOGLE_ANALYTICS_TRACKER_ID");
```

You can also test your tracks (during the development phase). It'll just print tracks in LogCat.
```java
   AnalyticsManager.getInstance().debugMode(context, true);
```

And, ... that's it. You don't have to import any XML file or whatever... You can now use the annotations to send tracks.

### Annotations

The annotations can only be used on methods. You just have to add them; and each time your method will be called, the track declared in your annotation will be sent.

###Event track :

```java
@TrackEvent(category = "ui_action", action = "on_click", label = "on_click_login_button", value = 1)
private void onLoginButtonClicked(){
  doYourStuff();
}
```

###Screen track :

```java
@TrackScreen(name = "MainActivity")
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  doYourStuff();
}
```

### Manual usage

For some reasons, you need to send a track switch some conditions, that's why you can't use the annotation system.
You still can use the `AnalyticsManager` manually.

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
  doYourStuff();
}
```

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  if(user.isLogged()){
    AnalyticsManager.getInstance().trackScreen("MainActivity");
  }
  doYourStuff();
}
```

## Future

- list of tracks
