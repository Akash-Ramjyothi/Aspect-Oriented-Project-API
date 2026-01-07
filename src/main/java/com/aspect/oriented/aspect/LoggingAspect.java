package com.aspect.oriented.aspect;

import com.aspect.oriented.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.classfile.MethodSignature;
import java.util.List;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    @AfterReturning(
            pointcut = "execution(* com.aspect.oriented.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdivce(JoinPoint theJoinPoint, List<Account> result) {

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        System.out.println("\n=====>>> Result is: " + result);
        
        convertAccountNamesToUpperCase(result);
        System.out.println("\n=====>>> Result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for(Account tempAccount:result){
            String theUpperName=tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);
        }
    }

    //    @Before("execution(public void updateAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
    @Before("com.aspect.oriented.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");

        org.aspectj.lang.reflect.MethodSignature methodSignature = (org.aspectj.lang.reflect.MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println("tempArg: " + tempArg);

            if (tempArg instanceof Account) {
                Account theAccount = (Account) tempArg;

                System.out.println("Account Name: " + theAccount.getName());
                System.out.println("Account Level: " + theAccount.getLevel());
            }
        }
    }
}
