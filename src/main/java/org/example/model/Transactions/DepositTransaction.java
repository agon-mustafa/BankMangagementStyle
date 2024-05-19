package org.example.model.Transactions;


import org.example.exception.AccountNotFoundException;
import org.example.model.Bank;

public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount, String accountId, String reason) {
        super(amount, null, accountId, reason);
    }

    @Override
    public void process(Bank bank) throws AccountNotFoundException {
        bank.deposit(resultingAccountId, amount);
    }
}
