package com.aspect.oriented.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //    @Before("execution(public void updateAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
    @Before("execution(* add*(com.aspect.oriented.Account))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }
}
