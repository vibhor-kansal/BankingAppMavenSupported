package com.capgemini.services.impl;

import com.capgemini.constants.BankingConstants;
import com.capgemini.exceptions.*;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.impl.AccountRepositoryImpl;
import com.capgemini.models.response.SearchAccountResponse;
import com.capgemini.services.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository = new AccountRepositoryImpl();

    public Account createAccount(long accountNumber, double initialAmount, Customer customer) throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException {

        if(initialAmount < BankingConstants.INITIAL_AMOUNT) {
            throw new InsufficientInitialBalanceException("initial balance should be 500 Rs.");
        }
        SearchAccountResponse searchAccountResponse = accountRepository.searchAccount(accountNumber);
        if(searchAccountResponse.getAccountExistenceFlag()) {
            throw new AccountDuplicationException("this account number is already associated with another account");
        }

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAmount(initialAmount);
        account.setCustomer(customer);

        if(accountRepository.createAccount(account))
            return account;
        else
            throw new ServerDowntimeException("Can not create account due to network failure.");
    }

    public Account depositAmount(long accountNumber, double amount) throws InvalidAccountNumberException, ServerDowntimeException {
        SearchAccountResponse searchAccountResponse = accountRepository.searchAccount(accountNumber);
        if(!searchAccountResponse.getAccountExistenceFlag()) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else {
            Account accountToDeposit = searchAccountResponse.getAccount();
            accountToDeposit.setAmount(accountToDeposit.getAmount() + amount);
            if(!accountRepository.updateAccount(accountToDeposit)) {
                throw new ServerDowntimeException("Could not withdraw the given amount due to network failure.");
            } else {
                return accountToDeposit;
            }
        }
    }

    public Account withdrawAmount(long accountNumber, double amount) throws InvalidAccountNumberException, InsufficientBalanceException, ServerDowntimeException {
        SearchAccountResponse searchAccountResponse = accountRepository.searchAccount(accountNumber);
        if(!searchAccountResponse.getAccountExistenceFlag()) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else {
            Account accountToWithdraw = searchAccountResponse.getAccount();
            if(accountToWithdraw.getAmount() < amount) {
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
    }

    public String fundTransfer(long fromAccount, long toAccount, double amount) throws InvalidAccountNumberException, InsufficientBalanceException {
        SearchAccountResponse sourceSearchAccountResponse = accountRepository.searchAccount(fromAccount);
        SearchAccountResponse destSearchAccountResponse = accountRepository.searchAccount(toAccount);
        if(!sourceSearchAccountResponse.getAccountExistenceFlag() || !destSearchAccountResponse.getAccountExistenceFlag()) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else {
            Account sourceAccount = sourceSearchAccountResponse.getAccount();
            Account destAccount = destSearchAccountResponse.getAccount();
            if(sourceAccount.getAmount() < amount) {
                throw new InsufficientBalanceException("Not enough balance to withdraw");
            } else {
                sourceAccount.setAmount(sourceAccount.getAmount() - amount);
                destAccount.setAmount(destAccount.getAmount() + amount);
                return "Amount transferred successfully \n " +
                        "New account balance for " + sourceAccount.getAccountNumber() + "is : " + sourceAccount.getAmount() +
                        "\n New account balance for " + destAccount.getAccountNumber() + "is : " + destAccount.getAmount();
            }
        }
    }

    public double showBalance(long accountNumber) throws InvalidAccountNumberException {
        SearchAccountResponse searchAccountResponse = accountRepository.searchAccount(accountNumber);
        if(!searchAccountResponse.getAccountExistenceFlag()) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        }
        Account account = searchAccountResponse.getAccount();
        return account.getAmount();
    }

    public String deleteAccount(long accountNumber) throws InvalidAccountNumberException, ServerDowntimeException {
        SearchAccountResponse searchAccountResponse = accountRepository.searchAccount(accountNumber);
        if(!searchAccountResponse.getAccountExistenceFlag()) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        }
        if(!searchAccountResponse.getAccountExistenceFlag()) {
            throw new InvalidAccountNumberException("Please enter a valid account number to operate.");
        } else {
            Account account = searchAccountResponse.getAccount();
            if(!accountRepository.deleteAccount(account)) {
                throw new ServerDowntimeException("Could not delete the account due to network failure.");
            }
            return "deleted successfully";
        }
    }
}
