package com.capgemini;

import com.capgemini.constants.BankingConstants;
import com.capgemini.exceptions.*;
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

public class AccountWithdrawlTest {

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

        account = accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer);
    }

    @Test
    public void testAccountWithdrawlSuccess() throws ServerDowntimeException, InvalidAccountNumberException, InsufficientBalanceException {
        assertEquals(accountService.withdrawAmount(12345678, 100), account);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void testAccountWithdrawlInvalidAccountNumberException() throws ServerDowntimeException, InvalidAccountNumberException, InsufficientInitialBalanceException, InsufficientBalanceException {
        assertEquals(accountService.withdrawAmount(1234, 100), account);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void testAccountWithdrawlInsufficientBalanceException() throws ServerDowntimeException, InvalidAccountNumberException, InsufficientInitialBalanceException, InsufficientBalanceException {
        assertEquals(accountService.withdrawAmount(12345678, 600), account);
    }

    @After
    public void tearDown() throws ServerDowntimeException, InvalidAccountNumberException {
        accountService.deleteAccount(account.getAccountNumber());
    }
}
