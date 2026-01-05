package com.aspect.oriented.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.aspect.oriented.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("execution(* com.aspect.oriented.dao.*.get*(..))")
    public void getter() {
    }

    @Pointcut("execution(* com.aspect.oriented.dao.*.set*(..))")
    public void setter() {
    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }
}
