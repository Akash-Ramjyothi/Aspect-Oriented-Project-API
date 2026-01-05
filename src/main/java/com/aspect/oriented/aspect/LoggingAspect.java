package com.aspect.oriented.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.classfile.MethodSignature;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    //    @Before("execution(public void updateAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
    @Before("com.aspect.oriented.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");

        org.aspectj.lang.reflect.MethodSignature methodSignature = (org.aspectj.lang.reflect.MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

    }
}
