package com.capgemini.repository;

import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;

public interface AccountRepository {

    public Boolean createAccount(Account account);

    public Account searchAccount(long accountNumber) throws InvalidAccountNumberException;

    public Boolean updateAccount(Account account);
}
