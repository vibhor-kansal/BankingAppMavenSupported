package com.capgemini;

import com.capgemini.constants.BankingConstants;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.ServerDowntimeException;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.services.AccountService;
import com.capgemini.services.CustomerService;
import com.capgemini.services.impl.AccountServiceImpl;
import com.capgemini.services.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AccountCreationTest {

    AccountService accountService;
    CustomerService customerService;
    Customer customer;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl();
        customerService = new CustomerServiceImpl();
        customer = customerService.addNewCustomer();
    }

    @Test
    public void testValidAccountCreation() throws InsufficientInitialBalanceException, ServerDowntimeException {
        Account account = new Account();
        account.setAccountNumber(12345678);
        account.setAmount(BankingConstants.INITIAL_AMOUNT);
        account.setCustomer(customer);

        when(accountRepository.createAccount(account)).thenReturn(true);
        assertEquals(accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer), account);
    }

    @Test(expected = InsufficientInitialBalanceException.class)
    public void testAccountCreationException() throws InsufficientInitialBalanceException, ServerDowntimeException {
        Account account = new Account();
        account.setAccountNumber(12345678);
        account.setAmount(100);

        when(accountRepository.createAccount(account)).thenReturn(true);
        assertEquals(accountService.createAccount(12345678, 100, customer), account);
    }
}
