package com.mbiamont.ga_annotations.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by mbiamont on 04/08/15.
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackEvent {

    String trackerId() default "";

    String category() default "category";

    String action() default "action";

    String label() default "label";

    long value() default 0;
}
