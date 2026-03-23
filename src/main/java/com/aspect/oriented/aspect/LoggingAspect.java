package com.aspect.oriented.aspect;

import com.aspect.oriented.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Logging Aspect
 *
 * Handles cross-cutting concerns like:
 * - Method execution tracing
 * - Performance monitoring
 * - Exception logging
 * - Data transformation
 */
@Aspect
@Component
@Order(2)
public class LoggingAspect {

    /**
     * Around advice for performance monitoring on service layer
     */
    @Around("execution(* com.aspect.oriented.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> [@Around] Executing method: " + method);

        long startTime = System.nanoTime();
        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            System.out.println("\n=====>>> [@Around] Exception: " + ex.getMessage());
            throw ex; // rethrow to maintain original behavior
        }

        long duration = System.nanoTime() - startTime;
        System.out.println("\n=====>>> [@Around] Execution time: " + duration + " ns");

        return result;
    }

    /**
     * After (finally) advice
     */
    @After("execution(* com.aspect.oriented.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> [@After] (finally) Executed: " + method);
    }

    /**
     * After throwing advice
     */
    @AfterThrowing(
            pointcut = "execution(* com.aspect.oriented.dao.AccountDAO.findAccounts(..))",
            throwing = "exception"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> [@AfterThrowing] Exception in: " + method);
        System.out.println("\n=====>>> Exception details: " + exception);
    }

    /**
     * After returning advice
     */
    @AfterReturning(
            pointcut = "execution(* com.aspect.oriented.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> [@AfterReturning] Method completed: " + method);

        System.out.println("\n=====>>> Original result: " + result);

        transformAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> Modified result: " + result);
    }

    /**
     * Before advice for DAO layer (excluding getters/setters)
     */
    @Before("com.aspect.oriented.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeDaoMethodAdvice(JoinPoint joinPoint) {

        System.out.println("\n====>>> [@Before] Executing DAO method");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            System.out.println("Argument: " + arg);

            if (arg instanceof Account account) {
                logAccountDetails(account);
            }
        }
    }

    /**
     * Helper method to transform account names
     */
    private void transformAccountNamesToUpperCase(List<Account> accounts) {

        if (accounts == null) return;

        for (Account account : accounts) {
            if (account.getName() != null) {
                account.setName(account.getName().toUpperCase());
            }
        }
    }

    /**
     * Helper method to log account details
     */
    private void logAccountDetails(Account account) {
        System.out.println("Account Name: " + account.getName());
        System.out.println("Account Level: " + account.getLevel());
    }
}
