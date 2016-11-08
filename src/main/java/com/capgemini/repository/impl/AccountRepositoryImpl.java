package com.capgemini.repository.impl;

import com.capgemini.models.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.models.response.SearchAccountResponse;

import java.util.HashSet;
import java.util.Set;

public class AccountRepositoryImpl implements AccountRepository {

    private static Set<Account> accounts = new HashSet();

    public Boolean createAccount(Account account) {
        accounts.add(account);
        System.out.println(accounts);
        return true;
    }

    public SearchAccountResponse searchAccount(long accountNumber) {
        for(Account account : accounts) {
            if(account.getAccountNumber() == accountNumber) {
                return new SearchAccountResponse(account, true);
            }
        }
        return new SearchAccountResponse(new Account(), false);
    }

    public Boolean updateAccount(Account accountToUpdate) {
        accounts.add(accountToUpdate);
        System.out.println(accounts);
        return true;
    }

    public Boolean deleteAccount(Account account) {
        accounts.remove(account);
        System.out.println(accounts);
        return true;
    }
}
