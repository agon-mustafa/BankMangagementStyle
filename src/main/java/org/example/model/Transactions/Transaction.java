package org.example.model.Transactions;



import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.model.Bank;

public abstract class Transaction {
    protected double amount;
    protected String originatingAccountId;
    protected String resultingAccountId;
    protected String reason;

    public Transaction(double amount, String originatingAccountId, String resultingAccountId, String reason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
    }

    public abstract void process(Bank bank) throws AccountNotFoundException, InsufficientFundsException;

    public String getReason() {
        return reason;
    }
}


