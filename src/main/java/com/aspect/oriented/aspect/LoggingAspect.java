package com.aspect.oriented.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.aspect.oriented.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(* com.aspect.oriented.dao.*.get*(..))")
    private void getter() {
    }

    @Pointcut("execution(* com.aspect.oriented.dao.*.set*(..))")
    private void setter() {
    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {
    }

    //    @Before("execution(public void updateAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performingApiAnalytics() {
        System.out.println("\n====>>> Performing API analytics");
    }
}
