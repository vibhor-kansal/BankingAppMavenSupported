package com.capgemini.models;

public class Account {

    private long accountNumber;
    private double amount;
    private Customer customer;

    public Account() {
    }

    public Account(long accountNumber, double amount, Customer customer) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.customer = customer;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getAccountNumber() != account.getAccountNumber()) return false;
        if (Double.compare(account.getAmount(), getAmount()) != 0) return false;
        return getCustomer().equals(account.getCustomer());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getAccountNumber() ^ (getAccountNumber() >>> 32));
        temp = Double.doubleToLongBits(getAmount());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCustomer().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", customer=" + customer +
                '}';
    }
}
