package com.capgemini.models.response;

import com.capgemini.models.Account;

public class SearchAccountResponse {

    private Account account;
    private Boolean accountExistenceFlag;

    public SearchAccountResponse(Account account, Boolean accountExistenceFlag) {
        this.account = account;
        this.accountExistenceFlag = accountExistenceFlag;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getAccountExistenceFlag() {
        return accountExistenceFlag;
    }

    public void setAccountExistenceFlag(Boolean accountExistenceFlag) {
        this.accountExistenceFlag = accountExistenceFlag;
    }
}
