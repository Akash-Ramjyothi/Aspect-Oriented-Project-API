package com.aspect.oriented.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    @Before("com.aspect.oriented.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void performingApiAnalytics() {
        System.out.println("\n====>>> Performing API analytics");
    }
}
