package com.aspect.oriented;

import com.aspect.oriented.dao.AccountDAO;
import com.aspect.oriented.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        return runner -> {
//            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
//            demoTheAfterReturningAdvice(theAccountDAO);
            demoTheAfterThrowingAdvie(theAccountDAO);
        };
    }

    private void demoTheAfterThrowingAdvie(AccountDAO theAccountDAO) {
        List<Account> theAccounts = null;

        try {
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Program: caught exception: " + exc);

        }

        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvie");
        System.out.println("----");

        System.out.println("theAccounts : " + theAccounts);
        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
        List<Account> theAccounts = theAccountDAO.findAccounts();

        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("----");

        System.out.println("theAccounts : " + theAccounts);
        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        Account theAccount = new Account();

        theAccount.setName("Madhu");
        theAccount.setLevel("Platinum");

        theAccountDAO.addAccount(theAccount, true);
        theAccountDAO.doWork();

        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();

        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();
    }
}
