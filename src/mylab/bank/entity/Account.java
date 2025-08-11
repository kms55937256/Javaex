package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) return;
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) return;
        if (balance < amount) {
            throw new InsufficientBalanceException("�ܾ��� �����մϴ�.");
        }
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("���¹�ȣ: %s, ������: %s, �ܾ�: %.1f��",
                accountNumber, ownerName, balance);
    }
}