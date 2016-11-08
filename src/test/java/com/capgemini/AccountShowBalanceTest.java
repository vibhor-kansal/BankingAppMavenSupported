package com.capgemini;

import com.capgemini.constants.BankingConstants;
import com.capgemini.exceptions.AccountDuplicationException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.exceptions.ServerDowntimeException;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.services.AccountService;
import com.capgemini.services.CustomerService;
import com.capgemini.services.impl.AccountServiceImpl;
import com.capgemini.services.impl.CustomerServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AccountShowBalanceTest {

    Account account;
    AccountService accountService;

    Customer customer;
    CustomerService customerService;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl();
        customerService = new CustomerServiceImpl();
        customer = customerService.addNewCustomer();

        account = new Account();
        account.setAccountNumber(12345678);
        account.setAmount(BankingConstants.INITIAL_AMOUNT);
        account.setCustomer(customer);

        accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer);
    }

    @Test
    public void testAccountShowBalanceSuccess() throws InvalidAccountNumberException {
        assertEquals(accountService.showBalance(12345678), BankingConstants.INITIAL_AMOUNT, 0);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void testAccountShowBalanceException() throws InvalidAccountNumberException {
        assertEquals(accountService.showBalance(1234), BankingConstants.INITIAL_AMOUNT, 0);
    }

    @After
    public void tearDown() throws ServerDowntimeException, InvalidAccountNumberException {
        accountService.deleteAccount(account.getAccountNumber());
    }
}
