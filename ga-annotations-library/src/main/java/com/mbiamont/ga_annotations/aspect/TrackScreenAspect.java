package com.mbiamont.ga_annotations.aspect;

import com.mbiamont.ga_annotations.AnalyticsManager;
import com.mbiamont.ga_annotations.annotation.TrackScreen;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by mbiamont on 04/08/15.
 */
@Aspect
public class TrackScreenAspect {
    public static TrackScreenAspect aspectOf() {
        return new TrackScreenAspect();
    }

    @Pointcut("within(@com.mbiamont.ga_annotations.annotation.TrackScreen *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(@com.mbiamont.ga_annotations.annotation.TrackScreen * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    @Pointcut("execution(@com.mbiamont.ga_annotations.annotation.TrackScreen *.new(..)) || constructorInsideAnnotatedType()")
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
        TrackScreen myAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TrackScreen.class);
        AnalyticsManager.getInstance().trackScreen(myAnnotation.trackerId(), myAnnotation.name());
        return joinPoint.proceed();
    }
}
