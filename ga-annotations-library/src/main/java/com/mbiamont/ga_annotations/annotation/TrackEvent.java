package com.mbiamont.ga_annotations.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by mbiamont on 04/08/15.
 */
@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(CLASS)
public @interface TrackEvent {

    String trackerId() default "";

    String category() default "category";

    String action() default "action";

    String label() default "label";

    long value() default 0;
}
