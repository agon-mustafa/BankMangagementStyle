package org.example.model;


public class Account {
    private String accountId;
    private String userName;
    private double balance;

    public Account(String accountId, String userName, double initialBalance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}
