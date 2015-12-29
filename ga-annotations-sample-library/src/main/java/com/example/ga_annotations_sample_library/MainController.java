package com.example.ga_annotations_sample_library;

import com.mbiamont.ga_annotations.annotation.TrackEvent;

/**
 * Created by mbiamont on 28/12/2015.
 */
public class MainController {

    @TrackEvent(trackerId = "TRACKERID5", category = "Stuff", action = "doStuff", label = "MainActivity")
    public static void doStuff(){
    }
}