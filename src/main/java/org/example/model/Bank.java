package org.example.model;




import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.model.Transactions.TransferTransaction;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFee;
    private double transactionPercentFee;

    public Bank(String bankName, double transactionFlatFee, double transactionPercentFee) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.transactionFlatFee = transactionFlatFee;
        this.transactionPercentFee = transactionPercentFee;
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
    }

    public Account createAccount(String accountId, String userName, double initialBalance) {
        Account account = new Account(accountId, userName, initialBalance);
        accounts.add(account);
        return account;
    }

    public Account findAccount(String accountId) throws AccountNotFoundException {
        return accounts.stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));
    }

    public void transfer(String fromAccountId, String toAccountId, double amount, String reason, boolean useFlatFee) throws AccountNotFoundException, InsufficientFundsException {
        Account fromAccount = findAccount(fromAccountId);
        Account toAccount = findAccount(toAccountId);

        double fee = useFlatFee ? transactionFlatFee : amount * transactionPercentFee / 100;
        if (fromAccount.getBalance() < amount + fee) {
            throw new InsufficientFundsException("Insufficient funds for transfer");
        }

        fromAccount.withdraw(amount + fee);
        toAccount.deposit(amount);
        totalTransactionFeeAmount += fee;
        totalTransferAmount += amount;

        // Record the transaction (simplified for this example)
        TransferTransaction transaction = new TransferTransaction(amount, fromAccountId, toAccountId, reason, useFlatFee);
        System.out.println("Transaction completed: " + transaction.getReason());
    }

    public void deposit(String accountId, double amount) throws AccountNotFoundException {
        Account account = findAccount(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account account = findAccount(accountId);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        account.withdraw(amount);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }
}
