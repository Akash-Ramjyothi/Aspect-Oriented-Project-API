package com.aspect.oriented.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyApiAnalyticsAspect {

    @Before("forDaoPackageNoGetterSetter()")
    public void performingApiAnalytics() {
        System.out.println("\n====>>> Performing API analytics");
    }
}
