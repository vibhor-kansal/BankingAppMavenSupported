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

import static org.junit.Assert.assertNotNull;

public class AccountFundTransferTest {

    Account account1;
    Account account2;
    AccountService accountService;

    Customer customer1;
    Customer customer2;
    CustomerService customerService;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() throws InsufficientInitialBalanceException, ServerDowntimeException, AccountDuplicationException {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl();
        customerService = new CustomerServiceImpl();
        customer1 = customerService.addNewCustomer();
        customer2 = customerService.addNewCustomer();

        account1 = new Account();
        account1.setAccountNumber(12345678);
        account1.setAmount(BankingConstants.INITIAL_AMOUNT);
        account1.setCustomer(customer1);

        accountService.createAccount(12345678, BankingConstants.INITIAL_AMOUNT, customer1);

        account2 = new Account();
        account2.setAccountNumber(1234567890);
        account2.setAmount(BankingConstants.INITIAL_AMOUNT);
        account2.setCustomer(customer2);

        accountService.createAccount(1234567890, BankingConstants.INITIAL_AMOUNT, customer2);
    }

    @Test
    public void testAccountFundTransferSuccess() throws InsufficientBalanceException, InvalidAccountNumberException {
        assertNotNull(accountService.fundTransfer(1234567890, 12345678, 100));
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void testAccountFundTransferInvalidSourceAccountNumberException() throws InsufficientBalanceException, InvalidAccountNumberException {
        assertNotNull(accountService.fundTransfer(12345, 12345678, 100));
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void testAccountFundTransferInvalidDestinationAccountNumberException() throws InsufficientBalanceException, InvalidAccountNumberException {
        assertNotNull(accountService.fundTransfer(12345678, 1234, 100));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void testAccountFundTransferInsufficientBalanceException() throws InsufficientBalanceException, InvalidAccountNumberException {
        assertNotNull(accountService.fundTransfer(1234567890, 12345678, 1000));
    }

    @After
    public void tearDown() throws ServerDowntimeException, InvalidAccountNumberException {
        accountService.deleteAccount(account1.getAccountNumber());
        accountService.deleteAccount(account2.getAccountNumber());
    }
}
