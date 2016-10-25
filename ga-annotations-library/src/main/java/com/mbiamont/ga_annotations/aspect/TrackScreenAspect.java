package com.mbiamont.ga_annotations.aspect;

import com.mbiamont.ga_annotations.AnalyticsManager;
import com.mbiamont.ga_annotations.annotation.TrackScreen;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;

/**
 * Created by mbiamont on 04/08/15.
 */
@Aspect
public class TrackScreenAspect {
    public static TrackScreenAspect aspectOf() {
        return new TrackScreenAspect();
    }


    @Pointcut("within(@com.mbiamont.ga_annotations.annotation.TrackScreen *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@com.mbiamont.ga_annotations.annotation.TrackScreen * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@com.mbiamont.ga_annotations.annotation.TrackScreen *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Around("method() || constructor()")
    public Object sendTrack(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) codeSignature;
        Annotation[] annotations = methodSignature.getMethod().getAnnotations();

        TrackScreen myAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TrackScreen.class);
        String trackerId = myAnnotation.trackerId();

        AnalyticsManager.getInstance().trackScreen(myAnnotation.trackerId(), myAnnotation.name());
        return joinPoint.proceed();
    }
}
