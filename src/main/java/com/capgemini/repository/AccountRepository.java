package com.capgemini.repository;

import com.capgemini.models.Account;
import com.capgemini.models.response.SearchAccountResponse;

public interface AccountRepository {

    public Boolean createAccount(Account account);

    public SearchAccountResponse searchAccount(long accountNumber);

    public Boolean updateAccount(Account account);

    public Boolean deleteAccount(Account account);
}
