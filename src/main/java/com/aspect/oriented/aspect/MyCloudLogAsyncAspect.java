package com.aspect.oriented.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

    @Before("com.aspect.oriented.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync(JoinPoint joinPoint) {

        // Capture details before async call
        String methodName = joinPoint.getSignature().toShortString();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        long timestamp = System.currentTimeMillis();

        // Async logging (non-blocking)
        CompletableFuture.runAsync(() -> {
            System.out.println("\n====>>> [CLOUD-ASYNC-LOG] Sending logs to cloud...");
            System.out.println("====>>> Method: " + methodName);
            System.out.println("====>>> Class: " + className);
            System.out.println("====>>> Timestamp: " + timestamp);

            if (args != null && args.length > 0) {
                System.out.println("====>>> Arguments: " + Arrays.toString(args));
            } else {
                System.out.println("====>>> No arguments passed");
            }

            System.out.println("====>>> [CLOUD-ASYNC-LOG] Log sent successfully!");
        }).exceptionally(ex -> {
            System.out.println("====>>> [CLOUD-ASYNC-LOG] Failed to send logs: " + ex.getMessage());
            return null;
        });
    }
}
