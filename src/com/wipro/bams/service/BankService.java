package com.wipro.bams.service;

import java.util.ArrayList;
import com.wipro.bams.entity.*;
import com.wipro.bams.util.*;

public class BankService {
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private static int transactionIdCounter = 1;

    public BankService(ArrayList<Customer> customers, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {
        this.customers = customers;
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public boolean validateCustomer(String customerId) throws InvalidCustomerException {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        throw new InvalidCustomerException("Error: Customer ID " + customerId + " not found.");
    }

    public Account getAccount(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public Transaction deposit(String accountId, double amount) throws TransactionException {
        if (amount <= 0) {
            throw new TransactionException("Error: Deposit amount must be positive.");
        }

        Account targetAccount = getAccount(accountId);
        if (targetAccount == null) {
            throw new TransactionException("Error: Account ID " + accountId + " not found for deposit.");
        }

        targetAccount.setBalance(targetAccount.getBalance() + amount);
        String newTid = "T" + String.format("%04d", transactionIdCounter++);
        
        // Using a placeholder date for simplicity
        Transaction newTransaction = new Transaction(newTid, accountId, "Deposit", amount, "2026-01-22");
        transactions.add(newTransaction);
        return newTransaction;
    }

    public Transaction withdraw(String accountId, double amount) throws InsufficientBalanceException, TransactionException {
        if (amount <= 0) {
            throw new TransactionException("Error: Withdrawal amount must be positive.");
        }

        Account targetAccount = getAccount(accountId);
        if (targetAccount == null) {
            throw new TransactionException("Error: Account ID " + accountId + " not found for withdrawal.");
        }

        if (targetAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Error: Insufficient balance for withdrawal of " + amount + ". Current balance: " + targetAccount.getBalance());
        }

        targetAccount.setBalance(targetAccount.getBalance() - amount);
        String newTid = "T" + String.format("%04d", transactionIdCounter++);

        // Using a placeholder date for simplicity
        Transaction newTransaction = new Transaction(newTid, accountId, "Withdrawal", amount, "2026-01-22");
        transactions.add(newTransaction);
        return newTransaction;
    }

    public void printTransactionHistory(String accountId) {
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.getAccountId().equals(accountId)) {
                found = true;
                System.out.println("ID: " + transaction.getTransactionId() + 
                                   ", Type: " + transaction.getTransactionType() + 
                                   ", Amount: " + transaction.getAmount() + 
                                   ", Date: " + transaction.getDate());
            }
        }
        if (!found) {
            System.out.println("No transaction history found for Account ID " + accountId);
        }
    }
}
