package org.example.model.Transactions;


import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.model.Bank;

public class TransferTransaction extends Transaction {
    private boolean useFlatFee;

    public TransferTransaction(double amount, String originatingAccountId, String resultingAccountId, String reason, boolean useFlatFee) {
        super(amount, originatingAccountId, resultingAccountId, reason);
        this.useFlatFee = useFlatFee;
    }

    @Override
    public void process(Bank bank) throws AccountNotFoundException, InsufficientFundsException {
        bank.transfer(originatingAccountId, resultingAccountId, amount, reason, useFlatFee);
    }
}
