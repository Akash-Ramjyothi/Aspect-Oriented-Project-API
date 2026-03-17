package com.aspect.oriented;

import com.aspect.oriented.dao.AccountDAO;
import com.aspect.oriented.dao.MembershipDAO;
import com.aspect.oriented.entity.Account;
import com.aspect.oriented.service.TrafficFortuneService;
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
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
                                               MembershipDAO membershipDAO,
                                               TrafficFortuneService trafficFortuneService) {
        return args -> {

            // Uncomment as needed to test specific AOP advice types

            // demoBeforeAdvice(accountDAO, membershipDAO);
            // demoAfterReturningAdvice(accountDAO);
            // demoAfterThrowingAdvice(accountDAO);
            // demoAfterAdvice(accountDAO);
            // demoAroundAdvice(trafficFortuneService);
            // demoAroundAdviceHandleException(trafficFortuneService);

            demoAroundAdviceReThrowException(trafficFortuneService);
        };
    }

    /**
     * Demonstrates @Around advice with exception rethrowing
     */
    private void demoAroundAdviceReThrowException(TrafficFortuneService service) {
        log("demoAroundAdviceReThrowException");

        try {
            boolean tripWire = true;
            String data = service.getFortune(tripWire);
            log("Fortune: " + data);
        } catch (Exception ex) {
            log("Exception rethrown to main: " + ex.getMessage());
        }

        log("Finished\n");
    }

    /**
     * Demonstrates @Around advice with exception handling
     */
    private void demoAroundAdviceHandleException(TrafficFortuneService service) {
        log("demoAroundAdviceHandleException");

        boolean tripWire = true;
        String data = service.getFortune(tripWire);

        log("Fortune: " + data);
        log("Finished\n");
    }

    /**
     * Demonstrates basic @Around advice
     */
    private void demoAroundAdvice(TrafficFortuneService service) {
        log("demoAroundAdvice");

        String data = service.getFortune();

        log("Fortune: " + data);
        log("Finished\n");
    }

    /**
     * Demonstrates @After (finally) advice
     */
    private void demoAfterAdvice(AccountDAO accountDAO) {
        log("demoAfterAdvice");

        List<Account> accounts = null;

        try {
            accounts = accountDAO.findAccounts(false);
        } catch (Exception ex) {
            log("Caught exception: " + ex.getMessage());
        }

        log("Accounts: " + accounts + "\n");
    }

    /**
     * Demonstrates @AfterThrowing advice
     */
    private void demoAfterThrowingAdvice(AccountDAO accountDAO) {
        log("demoAfterThrowingAdvice");

        List<Account> accounts = null;

        try {
            accounts = accountDAO.findAccounts(true);
        } catch (Exception ex) {
            log("Caught exception: " + ex.getMessage());
        }

        log("Accounts: " + accounts + "\n");
    }

    /**
     * Demonstrates @AfterReturning advice
     */
    private void demoAfterReturningAdvice(AccountDAO accountDAO) {
        log("demoAfterReturningAdvice");

        List<Account> accounts = accountDAO.findAccounts();

        log("Accounts: " + accounts + "\n");
    }

    /**
     * Demonstrates @Before advice
     */
    private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        log("demoBeforeAdvice");

        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");

        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        log("Account Name: " + accountDAO.getName());
        log("Service Code: " + accountDAO.getServiceCode());

        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        log("Finished\n");
    }

    /**
     * Utility logger for consistent output
     */
    private void log(String message) {
        System.out.println("\n[APP] " + message);
    }
}
