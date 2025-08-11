package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit; // 출금 한도

    public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다.");
        }
        super.withdraw(amount); // 잔액부족 검사는 부모에서 처리
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", 출금 한도: %.1f원", withdrawalLimit);
    }
}