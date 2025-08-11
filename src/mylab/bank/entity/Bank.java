package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber; // ��: 1000���� AC1000, AC1001...

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    // ����: AC + running number
    private String nextNo() { return "AC" + (nextAccountNumber++); }

    public String createSavingsAccount(String owner, double initial, double rate) {
        String no = nextNo();
        accounts.add(new SavingsAccount(no, owner, initial, rate));
        System.out.printf("Saving(����) ���°� �����Ǿ����ϴ�: %s%n",
                new SavingsAccount(no, owner, initial, rate).toString()); // �ȳ� ������
        return no;
    }

    public String createCheckingAccount(String owner, double initial, double limit) {
        String no = nextNo();
        accounts.add(new CheckingAccount(no, owner, initial, limit));
        System.out.printf("üŷ ���°� �����Ǿ����ϴ�: %s%n",
                new CheckingAccount(no, owner, initial, limit).toString()); // �ȳ� ������
        return no;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber)) return a;
        }
        throw new AccountNotFoundException("���� ��ȣ " + accountNumber + "�� ã�� �� �����ϴ�.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account a = findAccount(accountNumber);
        a.deposit(amount);
        System.out.printf("%.1f���� �ԱݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, a.getBalance());
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account a = findAccount(accountNumber);
        a.withdraw(amount);
        System.out.printf("%.1f���� ��ݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, a.getBalance());
    }

    public void transfer(String from, String to, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account src = findAccount(from);
        Account dst = findAccount(to);
        src.withdraw(amount);
        dst.deposit(amount);
        System.out.printf("%.1f���� %s���� %s�� �۱ݵǾ����ϴ�.%n", amount, from, to);
    }

    public void printAllAccounts() {
        System.out.println("���� ��� ���� ��� ����");
        for (Account a : accounts) {
            System.out.println(a.toString());
        }
        System.out.println();
    }

    // ���� ������ ������ ���� ����(���𿡼� ����)
    public List<Account> getAccounts() { return accounts; }
}