package com.aspect.oriented.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    @Before("com.aspect.oriented.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void performingApiAnalytics(JoinPoint joinPoint) {

        // Method Signature
        String methodName = joinPoint.getSignature().toShortString();

        // Method Arguments
        Object[] args = joinPoint.getArgs();

        System.out.println("\n====>>> [API ANALYTICS] Method Invoked: " + methodName);

        if (args != null && args.length > 0) {
            System.out.println("====>>> [API ANALYTICS] Arguments: " + Arrays.toString(args));
        } else {
            System.out.println("====>>> [API ANALYTICS] No arguments passed");
        }

        // Target Class
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("====>>> [API ANALYTICS] Target Class: " + className);

        // Timestamp
        System.out.println("====>>> [API ANALYTICS] Timestamp: " + System.currentTimeMillis());
    }
}
