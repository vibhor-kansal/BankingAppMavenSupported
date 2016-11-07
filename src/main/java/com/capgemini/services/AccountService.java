package com.capgemini.services;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.exceptions.ServerDowntimeException;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;

public interface AccountService {

    public Account createAccount(long accountNumber, double initialAmount, Customer customer) throws InsufficientInitialBalanceException, ServerDowntimeException;

    public Account depositAmount(long accountNumber, double amount) throws InvalidAccountNumberException, ServerDowntimeException;

    public Account withdrawAmount(long accountNumber, double amount) throws InvalidAccountNumberException, InsufficientBalanceException, ServerDowntimeException;

    public String fundTransfer(long fromAccount, long toAccount, double amount) throws InvalidAccountNumberException, InsufficientBalanceException;

    public double showBalance(long accountNumber) throws InvalidAccountNumberException;
}
