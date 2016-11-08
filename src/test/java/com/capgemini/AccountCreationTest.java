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

public class AccountCreationTest {

    Account account;
    AccountService accountService;

    Customer customer;
    CustomerService customerService;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl();
        customerService = new CustomerServiceImpl();
        customer = customerService.addNewCustomer();

        account = new Account();
        account.setAccountNumber(12345678);
        account.setCustomer(customer);
    }

    @Test
    public void testValidAccountCreation() throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException {
        account.setAmount(BankingConstants.INITIAL_AMOUNT);

        when(accountRepository.createAccount(account)).thenReturn(true);
        assertEquals(accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer), account);
    }

    @Test(expected = AccountDuplicationException.class)
    public void testAccountCreationAccountDuplicationException() throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException {
        account.setAmount(100);

        when(accountRepository.createAccount(account)).thenReturn(true);
        accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer);

        assertEquals(accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer), account);
    }

    /*@Test(expected = InsufficientInitialBalanceException.class)
    public void testAccountCreationException() throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException {
        account.setAmount(100);

        when(accountRepository.createAccount(account)).thenReturn(true);
        assertEquals(accountService.createAccount(12345678, 100, customer), account);
    }*/

    @After
    public void tearDown() throws ServerDowntimeException, InvalidAccountNumberException {
        accountService.deleteAccount(account.getAccountNumber());
    }
}
