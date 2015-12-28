package com.mbiamont.ga_annotations.aspect;

import com.mbiamont.ga_annotations.AnalyticsManager;
import com.mbiamont.ga_annotations.annotation.TrackEvent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by mbiamont on 04/08/15.
 */
@Aspect
public class TrackEventAspect {

    public static TrackEventAspect aspectOf() {
        return new TrackEventAspect();
    }

    @Pointcut("within(@com.mbiamont.ga_annotations.annotation.TrackEvent *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(@com.mbiamont.ga_annotations.annotation.TrackEvent * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Pointcut("execution(@com.mbiamont.ga_annotations.annotation.TrackEvent *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Pointcut("execution(* *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(*.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {
    }

    @Around("method() || constructor()")
    public Object sendTrack(ProceedingJoinPoint joinPoint) throws Throwable {
        TrackEvent myAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TrackEvent.class);

        AnalyticsManager.getInstance().trackEvent(
                myAnnotation.trackerId(),
                myAnnotation.category(),
                myAnnotation.action(),
                myAnnotation.label(),
                myAnnotation.value());

        return joinPoint.proceed();
    }
}
