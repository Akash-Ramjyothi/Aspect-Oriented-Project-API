package com.aspect.oriented.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Centralized AOP Pointcut Expressions
 * 
 * This class defines reusable pointcuts for the DAO layer.
 * Helps in maintaining clean and modular aspect configurations.
 */
@Aspect
public class AopExpressions {

    /**
     * Pointcut for all methods in DAO package
     */
    @Pointcut("execution(* com.aspect.oriented.dao.*.*(..))")
    public void forDaoPackage() {}

    /**
     * Pointcut for getter methods in DAO package
     */
    @Pointcut("execution(* com.aspect.oriented.dao.*.get*(..))")
    public void getter() {}

    /**
     * Pointcut for setter methods in DAO package
     */
    @Pointcut("execution(* com.aspect.oriented.dao.*.set*(..))")
    public void setter() {}

    /**
     * Pointcut for DAO methods excluding getters and setters
     */
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

    /**
     * Pointcut for service layer methods (future extensibility)
     */
    @Pointcut("execution(* com.aspect.oriented.service.*.*(..))")
    public void forServicePackage() {}

    /**
     * Pointcut for controller layer methods (future extensibility)
     */
    @Pointcut("execution(* com.aspect.oriented.controller.*.*(..))")
    public void forControllerPackage() {}

    /**
     * Combined pointcut for entire application (DAO + Service + Controller)
     */
    @Pointcut("forDaoPackage() || forServicePackage() || forControllerPackage()")
    public void forAppFlow() {}
}
