package com.capgemini.services.impl;

import com.capgemini.constants.BankingConstants;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.exceptions.ServerDowntimeException;
import com.capgemini.helpers.GenerateRandomValues;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.impl.AccountRepositoryImpl;
import com.capgemini.services.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public Account createAccount(long accountNumber, double initialAmount, Customer customer) throws InsufficientInitialBalanceException, ServerDowntimeException {

        if(initialAmount < BankingConstants.INITIAL_AMOUNT) {
            throw new InsufficientInitialBalanceException("initial balance should be 500 Rs.");
        }

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAmount(initialAmount);
        account.setCustomer(customer);

        accountRepository = new AccountRepositoryImpl();
        if(accountRepository.createAccount(account))
            return account;
        else
            throw new ServerDowntimeException("Can not create account due to network failure.");
    }

    @Override
    public Account depositAmount(long accountNumber, double amount) throws InvalidAccountNumberException, ServerDowntimeException {
        accountRepository = new AccountRepositoryImpl();
        Account accountToDeposit = accountRepository.searchAccount(accountNumber);
        if(accountToDeposit == null) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else {
            accountToDeposit.setAmount(accountToDeposit.getAmount() + amount);
            if(!accountRepository.updateAccount(accountToDeposit)) {
                throw new ServerDowntimeException("Could not withdraw the given amount due to network failure.");
            } else {
                return accountToDeposit;
            }
        }
    }

    @Override
    public Account withdrawAmount(long accountNumber, double amount) throws InvalidAccountNumberException, InsufficientBalanceException, ServerDowntimeException {
        accountRepository = new AccountRepositoryImpl();
        Account accountToWithdraw = accountRepository.searchAccount(accountNumber);
        if(accountToWithdraw == null) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else if(accountToWithdraw.getAmount() < amount) {
            throw new InsufficientBalanceException("Not enough balance to withdraw");
        } else {
            accountToWithdraw.setAmount(accountToWithdraw.getAmount() - amount);
            if(!accountRepository.updateAccount(accountToWithdraw)) {
                throw new ServerDowntimeException("Could not withdraw the given amount due to network failure.");
            } else {
                return accountToWithdraw;
            }
        }
    }

    @Override
    public String fundTransfer(long fromAccount, long toAccount, double amount) throws InvalidAccountNumberException, InsufficientBalanceException {
        accountRepository = new AccountRepositoryImpl();
        Account sourceAccount = accountRepository.searchAccount(fromAccount);
        Account destAccount = accountRepository.searchAccount(toAccount);
        if(sourceAccount == null || destAccount == null) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else if(sourceAccount.getAmount() < amount) {
            throw new InsufficientBalanceException("Not enough balance to withdraw");
        } else {
            sourceAccount.setAmount(sourceAccount.getAmount() - amount);
            destAccount.setAmount(destAccount.getAmount() + amount);
            return "Amount transferred successfully \n " +
                    "New account balance for " + sourceAccount.getAccountNumber() + "is : " + sourceAccount.getAmount() +
                    "\n New account balance for " + destAccount.getAccountNumber() + "is : " + destAccount.getAmount();
        }
    }

    @Override
    public double showBalance(long accountNumber) throws InvalidAccountNumberException {
        Account account = accountRepository.searchAccount(accountNumber);
        if(account == null) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        }
        return account.getAmount();
    }
}
