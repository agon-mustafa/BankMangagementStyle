package org.example;




import org.example.exception.AccountNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.model.*;
import org.example.model.Transactions.DepositTransaction;
import org.example.model.Transactions.Transaction;
import org.example.model.Transactions.TransferTransaction;
import org.example.model.Transactions.WithdrawalTransaction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSystemApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("Raiffeisen", 10.0, 5.0);

        while (true) {
            System.out.println("\n--- Bank System Menu ---");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check account balance");
            System.out.println("6. List all accounts");
            System.out.println("7. Check total transaction fees");
            System.out.println("8. Check total transfer amount");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                try {
                    switch (option) {
                        case 1:
                            System.out.print("Enter account ID: ");
                            String accountId = scanner.nextLine();
                            System.out.print("Enter user name: ");
                            String userName = scanner.nextLine();
                            System.out.print("Enter initial balance: ");
                            double initialBalance = scanner.nextDouble();
                            bank.createAccount(accountId, userName, initialBalance);
                            System.out.println("Account created successfully.");
                            break;
                        case 2:
                            System.out.print("Enter account ID: ");
                            accountId = scanner.nextLine();
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            Transaction deposit = new DepositTransaction(depositAmount, accountId, "Deposit");
                            deposit.process(bank);
                            System.out.println("Deposit successful.");
                            break;
                        case 3:
                            System.out.print("Enter account ID: ");
                            accountId = scanner.nextLine();
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawAmount = scanner.nextDouble();
                            Transaction withdrawal = new WithdrawalTransaction(withdrawAmount, accountId, "Withdrawal");
                            withdrawal.process(bank);
                            System.out.println("Withdrawal successful.");
                            break;
                        case 4:
                            System.out.print("Enter originating account ID: ");
                            String fromAccountId = scanner.nextLine();
                            System.out.print("Enter resulting account ID: ");
                            String toAccountId = scanner.nextLine();
                            System.out.print("Enter transfer amount: ");
                            double transferAmount = scanner.nextDouble();
                            System.out.print("Enter reason for transfer: ");
                            scanner.nextLine(); // Consume newline
                            String reason = scanner.nextLine();
                            System.out.print("Use flat fee? (true/false): ");
                            boolean useFlatFee = scanner.nextBoolean();
                            Transaction transfer = new TransferTransaction(transferAmount, fromAccountId, toAccountId, reason, useFlatFee);
                            transfer.process(bank);
                            System.out.println("Transfer successful.");
                            break;
                        case 5:
                            System.out.print("Enter account ID: ");
                            accountId = scanner.nextLine();
                            Account account = bank.findAccount(accountId);
                            System.out.println("Account balance: $" + account.getBalance());
                            break;
                        case 6:
                            for (Account acc : bank.getAllAccounts()) {
                                System.out.println("Account ID: " + acc.getAccountId() + ", User Name: " + acc.getUserName() + ", Balance: $" + acc.getBalance());
                            }
                            break;
                        case 7:
                            System.out.println("Total transaction fee amount: $" + bank.getTotalTransactionFeeAmount());
                            break;
                        case 8:
                            System.out.println("Total transfer amount: $" + bank.getTotalTransferAmount());
                            break;
                        case 9:
                            System.out.println("Exiting...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option. Please choose a number between 1 and 9.");
                    }
                } catch (AccountNotFoundException | InsufficientFundsException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.next();
            }
        }
    }
}
