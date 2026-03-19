package com.aspect.oriented.service;

/**
 * Service interface for providing traffic-related fortune messages.
 * Supports basic fortune retrieval as well as conditional execution
 * for testing scenarios (e.g., AOP, exception handling).
 */
public interface TrafficFortuneService {

    /**
     * Retrieve a standard traffic fortune message.
     *
     * @return fortune message
     */
    String getFortune();

    /**
     * Retrieve a traffic fortune message with an option to simulate
     * an exception (used for AOP testing like @AfterThrowing).
     *
     * @param tripWire flag to trigger an exception scenario
     * @return fortune message
     */
    String getFortune(boolean tripWire);
}
