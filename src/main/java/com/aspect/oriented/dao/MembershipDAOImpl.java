package com.aspect.oriented.dao;

import org.springframework.stereotype.Repository;

/**
 * DAO implementation for membership-related operations.
 * Simulates database interactions for demo and AOP use cases.
 */
@Repository
public class MembershipDAOImpl implements MembershipDAO {

    /**
     * Simulates adding a membership record to the database.
     *
     * @return true if operation is successful
     */
    @Override
    public boolean addSillyMember() {
        log("ADDING A MEMBERSHIP ACCOUNT");
        return true;
    }

    /**
     * Simulates an idle/wait state, useful for demonstrating AOP behavior
     * such as @Before, @After, or @Around advice.
     */
    @Override
    public void goToSleep() {
        log("Entering sleep mode");
    }

    /**
     * Utility method for consistent logging format.
     *
     * @param message log message
     */
    private void log(String message) {
        System.out.println(getClass().getSimpleName() + " : " + message);
    }
}
