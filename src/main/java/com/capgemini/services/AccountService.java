package com.capgemini.services;

import com.capgemini.exceptions.*;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;

public interface AccountService {

    public Account createAccount(long accountNumber, double initialAmount, Customer customer) throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException;

    public Account depositAmount(long accountNumber, double amount) throws InvalidAccountNumberException, ServerDowntimeException;

    public Account withdrawAmount(long accountNumber, double amount) throws InvalidAccountNumberException, InsufficientBalanceException, ServerDowntimeException;

    public String fundTransfer(long fromAccount, long toAccount, double amount) throws InvalidAccountNumberException, InsufficientBalanceException;

    public double showBalance(long accountNumber) throws InvalidAccountNumberException;

    public String deleteAccount(long accountNumber) throws InvalidAccountNumberException, ServerDowntimeException;
}
