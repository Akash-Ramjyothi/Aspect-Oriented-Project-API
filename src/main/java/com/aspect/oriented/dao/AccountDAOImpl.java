package com.aspect.oriented.dao;

import com.aspect.oriented.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    // Simulated in-memory storage
    private final List<Account> accountStore = new ArrayList<>();

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        if (tripWire) {
            throw new RuntimeException("Simulated exception: unable to fetch accounts");
        }

        // Return copy to avoid external modification
        return new ArrayList<>(accountStore);
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {

        System.out.println(getClass() + " : Adding account -> " + theAccount);

        if (vipFlag) {
            System.out.println(getClass() + " : VIP account detected");
        }

        accountStore.add(theAccount);
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " : Performing work...");

        // Simulate some logic
        return true;
    }

    // Additional CRUD-like methods

    public Optional<Account> findAccountByName(String accountName) {
        return accountStore.stream()
                .filter(acc -> acc.getName().equalsIgnoreCase(accountName))
                .findFirst();
    }

    public boolean updateAccount(String accountName, String newLevel) {
        Optional<Account> accountOpt = findAccountByName(accountName);

        if (accountOpt.isPresent()) {
            accountOpt.get().setLevel(newLevel);
            return true;
        }

        return false;
    }

    public boolean deleteAccount(String accountName) {
        return accountStore.removeIf(acc ->
                acc.getName().equalsIgnoreCase(accountName));
    }

    public void clearAccounts() {
        accountStore.clear();
    }

    // Getters & Setters with logging (useful for AOP demos)

    public String getName() {
        System.out.println(getClass() + " : in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " : in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " : in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " : in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
