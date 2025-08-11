package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber; // 예: 1000부터 AC1000, AC1001...

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    // 공통: AC + running number
    private String nextNo() { return "AC" + (nextAccountNumber++); }

    public String createSavingsAccount(String owner, double initial, double rate) {
        String no = nextNo();
        accounts.add(new SavingsAccount(no, owner, initial, rate));
        System.out.printf("Saving(저축) 계좌가 생성되었습니다: %s%n",
                new SavingsAccount(no, owner, initial, rate).toString()); // 안내 문구용
        return no;
    }

    public String createCheckingAccount(String owner, double initial, double limit) {
        String no = nextNo();
        accounts.add(new CheckingAccount(no, owner, initial, limit));
        System.out.printf("체킹 계좌가 생성되었습니다: %s%n",
                new CheckingAccount(no, owner, initial, limit).toString()); // 안내 문구용
        return no;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber)) return a;
        }
        throw new AccountNotFoundException("계좌 번호 " + accountNumber + "를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account a = findAccount(accountNumber);
        a.deposit(amount);
        System.out.printf("%.1f원이 입금되었습니다. 현재 잔액: %.1f원%n", amount, a.getBalance());
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account a = findAccount(accountNumber);
        a.withdraw(amount);
        System.out.printf("%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n", amount, a.getBalance());
    }

    public void transfer(String from, String to, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account src = findAccount(from);
        Account dst = findAccount(to);
        src.withdraw(amount);
        dst.deposit(amount);
        System.out.printf("%.1f원이 %s에서 %s로 송금되었습니다.%n", amount, from, to);
    }

    public void printAllAccounts() {
        System.out.println("── 모든 계좌 목록 ──");
        for (Account a : accounts) {
            System.out.println(a.toString());
        }
        System.out.println();
    }

    // 샘플 데이터 생성을 위해 노출(데모에서 쓰기)
    public List<Account> getAccounts() { return accounts; }
}