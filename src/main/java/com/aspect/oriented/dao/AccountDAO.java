package com.aspect.oriented.dao;

import com.aspect.oriented.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);
}
