package com.capgemini.initializer;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.exceptions.ServerDowntimeException;
import com.capgemini.models.Account;
import com.capgemini.models.Customer;
import com.capgemini.services.AccountService;
import com.capgemini.services.CustomerService;
import com.capgemini.services.impl.AccountServiceImpl;
import com.capgemini.services.impl.CustomerServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;

@Component
public class AppInitializer {

    private static Scanner sc = new Scanner(System.in);
    private CustomerService customerService;
    private AccountService accountService;

    public void showMenu() throws Exception {
        int choice;
        while (true) {
            System.out.println("1.create new account");
            System.out.println("2.money deposit");
            System.out.println("3.cash withdraw");
            System.out.println("4.fund transfer");
            System.out.println("5.show balance");
            System.out.println("6.exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositAmount();
                    break;
                case 3:
                    withdrawAmount();
                    break;
                case 4:
                    fundTransfer();
                    break;
                case 5:
                    showAccountBalance();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }

    public Set<Customer> findAllCustomers() {
        customerService = new CustomerServiceImpl();
        return customerService.findAll();
    }

    private void createAccount() throws InsufficientInitialBalanceException, ServerDowntimeException {
        customerService = new CustomerServiceImpl();
        accountService = new AccountServiceImpl();
        System.out.println("Please enter the account number you wish to have : ");
        long accountNumber = sc.nextLong();
        System.out.println("Please enter the initial amount for your account : ");
        double initialAmount = sc.nextDouble();
        System.out.println(accountService.createAccount(accountNumber, initialAmount, customerService.addNewCustomer()));
    }

    private void depositAmount() throws InvalidAccountNumberException, ServerDowntimeException {
        System.out.println("Please enter your account number : ");
        long accountNumber = sc.nextLong();
        System.out.println("Please enter the amount to deposit : ");
        double amountToDeposit = sc.nextDouble();
        Account updateAccount = accountService.depositAmount(accountNumber, amountToDeposit);
        System.out.println("New balance for account " + updateAccount.getAccountNumber() + " is : " + updateAccount.getAmount());
    }

    private void withdrawAmount() throws InvalidAccountNumberException, InsufficientBalanceException, ServerDowntimeException {
        System.out.println("Please enter your account number : ");
        long accountNumber = sc.nextLong();
        System.out.println("Please enter the amount to withdraw : ");
        double amountToWithdraw = sc.nextDouble();
        Account updateAccount = accountService.withdrawAmount(accountNumber, amountToWithdraw);
        System.out.println("New balance for account " + updateAccount.getAccountNumber() + " is : " + updateAccount.getAmount());
    }

    private void fundTransfer() throws InvalidAccountNumberException, InsufficientBalanceException {
        System.out.println("Please enter source account number : ");
        long fromAccount = sc.nextLong();
        System.out.println("Please enter destination account number : ");
        long toAccount = sc.nextLong();
        System.out.println("Please enter the amount to transfer : ");
        double amountToTransfer = sc.nextDouble();
        System.out.println(accountService.fundTransfer(fromAccount, toAccount, amountToTransfer));
    }

    private void showAccountBalance() throws InvalidAccountNumberException {
        System.out.println("Please enter your account number : ");
        long accountNumber = sc.nextLong();
        System.out.println("Balance for account number " + accountNumber + " is : " + accountService.showBalance(accountNumber));
    }
}
