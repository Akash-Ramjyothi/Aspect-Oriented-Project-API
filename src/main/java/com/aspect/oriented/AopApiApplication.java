package com.aspect.oriented;

import com.aspect.oriented.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO) {
        return runner -> {
            demoTheBeforeAdvice(theAccountDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO) {
        theAccountDAO.addAccount();

        System.out.println("\nlet's call it again!\n");

        theAccountDAO.addAccount();
    }
}
