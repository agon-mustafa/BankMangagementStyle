package org.example.model.Transactions;


import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.model.Bank;

public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount, String accountId, String reason) {
        super(amount, accountId, null, reason);
    }

    @Override
    public void process(Bank bank) throws AccountNotFoundException, InsufficientFundsException {
        bank.withdraw(originatingAccountId, amount);
    }
}
