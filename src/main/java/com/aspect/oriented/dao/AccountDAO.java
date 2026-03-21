package com.aspect.oriented.dao;

import com.aspect.oriented.Account;
import com.aspect.oriented.exception.DAOException;
import com.aspect.oriented.model.AccountFilter;
import com.aspect.oriented.model.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Data Access Object interface for Account entities.
 * Provides CRUD operations and query methods with AOP-friendly design.
 * 
 * @author Development Team
 * @version 2.0
 */
public interface AccountDAO {

    // ========== CREATE OPERATIONS ==========
    
    /**
     * Adds a new account to the data store.
     * 
     * @param theAccount the account to add (must not be null)
     * @param vipFlag indicates if the account has VIP status
     * @return the generated account ID
     * @throws IllegalArgumentException if account is null
     * @throws DAOException if database operation fails
     */
    Long addAccount(Account theAccount, boolean vipFlag) throws DAOException;
    
    /**
     * Adds multiple accounts in batch.
     * 
     * @param accounts list of accounts to add
     * @return number of successfully added accounts
     */
    int addAccounts(List<Account> accounts);
    
    /**
     * Asynchronously adds an account.
     * 
     * @param theAccount the account to add
     * @param vipFlag VIP status flag
     * @return CompletableFuture with the generated account ID
     */
    CompletableFuture<Long> addAccountAsync(Account theAccount, boolean vipFlag);

    // ========== READ OPERATIONS ==========
    
    /**
     * Retrieves all accounts from the data store.
     * 
     * @return list of all accounts (never null, may be empty)
     * @throws DAOException if database operation fails
     */
    List<Account> findAllAccounts() throws DAOException;
    
    /**
     * Finds account by its unique identifier.
     * 
     * @param id the account ID
     * @return Optional containing the account if found
     */
    Optional<Account> findAccountById(Long id);
    
    /**
     * Retrieves accounts with optional filtering.
     * 
     * @param tripWire if true, simulates a database exception for testing AOP advice
     * @return list of accounts matching the criteria
     * @throws DAOException when tripWire is true or actual database error occurs
     */
    List<Account> findAccounts(boolean tripWire) throws DAOException;
    
    /**
     * Finds accounts with pagination support.
     * 
     * @param pageable pagination parameters
     * @return paginated list of accounts
     */
    List<Account> findAccounts(Pageable pageable);
    
    /**
     * Finds accounts matching the specified filter criteria.
     * 
     * @param filter filter criteria for accounts
     * @return list of accounts matching the filter
     */
    List<Account> findAccounts(AccountFilter filter);
    
    /**
     * Finds accounts by account holder name (case-insensitive, partial match).
     * 
     * @param name the name to search for
     * @return list of accounts with matching names
     */
    List<Account> findAccountsByName(String name);
    
    /**
     * Counts total number of accounts.
     * 
     * @return total account count
     */
    long countAccounts();
    
    /**
     * Checks if an account exists with the given email.
     * 
     * @param email the email to check
     * @return true if account exists
     */
    boolean existsByEmail(String email);

    // ========== UPDATE OPERATIONS ==========
    
    /**
     * Updates an existing account.
     * 
     * @param theAccount the account with updated information
     * @return true if update was successful, false if account not found
     */
    boolean updateAccount(Account theAccount);
    
    /**
     * Updates the VIP status of an account.
     * 
     * @param accountId the account ID
     * @param vipFlag new VIP status
     * @return true if update was successful
     */
    boolean updateVipStatus(Long accountId, boolean vipFlag);
    
    /**
     * Performs business work operation.
     * 
     * @return true if work was completed successfully
     */
    boolean doWork();

    // ========== DELETE OPERATIONS ==========
    
    /**
     * Deletes an account by its ID.
     * 
     * @param id the account ID to delete
     * @return true if deletion was successful
     */
    boolean deleteAccount(Long id);
    
    /**
     * Deletes all accounts (use with caution).
     * 
     * @return number of deleted accounts
     */
    int deleteAllAccounts();

    // ========== BULK OPERATIONS ==========
    
    /**
     * Updates multiple accounts in a transaction.
     * 
     * @param accounts list of accounts to update
     * @return array of update results
     */
    int[] batchUpdateAccounts(List<Account> accounts);
    
    /**
     * Transfers amount between accounts.
     * 
     * @param fromAccountId source account ID
     * @param toAccountId destination account ID
     * @param amount amount to transfer
     * @return true if transfer was successful
     */
    boolean transferBalance(Long fromAccountId, Long toAccountId, double amount);

    // ========== ACCOUNT PROPERTIES ==========
    
    /**
     * Gets the account holder name.
     * 
     * @return the account holder name
     */
    String getName();
    
    /**
     * Sets the account holder name.
     * 
     * @param name the account holder name
     */
    void setName(String name);
    
    /**
     * Gets the service code for account operations.
     * 
     * @return the service code
     */
    String getServiceCode();
    
    /**
     * Sets the service code for account operations.
     * 
     * @param serviceCode the service code
     */
    void setServiceCode(String serviceCode);
    
    /**
     * Gets the current account balance.
     * 
     * @return the account balance
     */
    double getBalance();
    
    /**
     * Sets the account balance.
     * 
     * @param balance new account balance
     */
    void setBalance(double balance);

    // ========== VALIDATION & UTILITY ==========
    
    /**
     * Validates account data before operations.
     * 
     * @param account the account to validate
     * @return true if account is valid
     */
    boolean validateAccount(Account account);
    
    /**
     * Clears any cached data.
     */
    void clearCache();
}
