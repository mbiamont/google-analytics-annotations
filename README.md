#Google Analytics Annotations

Android library to use Google Analytics through annotations.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Google%20Analytics%20Annotations-green.svg?style=flat)](https://android-arsenal.com/details/1/2365)

[![Maven central](https://img.shields.io/maven-central/v/com.github.mbiamont/ga-annotations-library.svg)](https://android-arsenal.com/details/1/2365)



# Installation

In your `build.gradle` file add these lines :
```java
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.github.mbiamont:ga-annotations-plugin:1.1.0'
    }
}

apply plugin: 'com.android.application'
apply plugin: 'GAAnnotations'

(...)

dependencies {
    (...)
    compile 'com.github.mbiamont:ga-annotations-library:1.1.0'
}
```

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

### Use multiple TrackerId
If you have to use multiple trackerId in your application, you can define for each event or screen the tracker to use.

Example :
```java
@TrackScreen(trackerId= "UA-XXXX1", name = "MainActivity")
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  (...)
}
```

### DebugMode
If you just want to check if the tracks are correctly implemented; you can use the debugMode. It'll print tracks in LogCat instead of send them.

Just replace `AnalyticsManager.getInstance().init()` by

```java
AnalyticsManager.getInstance().debugMode(context, true);
```

### Manual track
Sometimes, you cannot use annotations (if your tracks require a condition for example). In this case, you can use trackers manually :
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

License
=======

    Copyright 2015 Melvin Biamont

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.Upda
