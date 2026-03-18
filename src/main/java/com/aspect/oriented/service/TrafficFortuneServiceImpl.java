package com.aspect.oriented.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {

    private static final int DEFAULT_DELAY_SECONDS = 5;

    @Override
    public String getFortune() {
        return getFortune(false, DEFAULT_DELAY_SECONDS);
    }

    @Override
    public String getFortune(boolean tripWire) {
        return getFortune(tripWire, DEFAULT_DELAY_SECONDS);
    }

    // Overloaded method with configurable delay
    public String getFortune(boolean tripWire, int delayInSeconds) {

        simulateDelay(delayInSeconds);

        if (tripWire) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }

        return buildFortuneMessage();
    }

    // Extracted delay logic
    private void simulateDelay(int delayInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted during delay", e);
        }
    }

    // Extracted message builder
    private String buildFortuneMessage() {
        return "Expect heavy traffic this morning";
    }
}
