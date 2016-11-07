package com.capgemini.repository.impl;

import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;
import com.capgemini.repository.AccountRepository;

import java.util.*;

public class AccountRepositoryImpl implements AccountRepository {

    private static Set<Account> accounts = new HashSet();
    Map<Customer, Account> customerAccountMap = new HashMap();

    public Boolean createAccount(Account account) {
        accounts.add(account);
        System.out.println(accounts);
        return true;
    }

    public Account searchAccount(long accountNumber) throws InvalidAccountNumberException {
        Account resultAccount = null;
        for(Account account : accounts) {
            if(account.getAccountNumber() == accountNumber) {
                resultAccount = account;
            }
        }
        return resultAccount;
    }

    public Boolean updateAccount(Account accountToUpdate) {
        accounts.add(accountToUpdate);
        System.out.println(accounts);
        return true;
    }
}
