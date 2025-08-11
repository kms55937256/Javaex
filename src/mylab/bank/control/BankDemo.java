package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // === 계좌 생성 ===
        String ac1 = bank.createSavingsAccount("홍길동", 10000.0, 0.03);
        String ac2 = bank.createCheckingAccount("김춘수", 20000.0, 5000.0);
        String ac3 = bank.createSavingsAccount("이영희", 30000.0, 0.02);

        System.out.println();
        bank.printAllAccounts();

        // === 입금/출금 테스트 ===
        try {
            System.out.println("── 입금/출금 테스트 ──");
            bank.deposit(ac1, 5000.0);
            bank.withdraw(ac1, 3000.0);
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("── 이자 적용 테스트 ──");
        for (Account a : bank.getAccounts()) {          
            if (a instanceof SavingsAccount) {          
                SavingsAccount sa = (SavingsAccount) a; 
                double before = sa.getBalance();
                double interest = before * sa.getInterestRate();
                sa.applyInterest();
                System.out.printf("이자 %.1f원이 적용되었습니다. 현재 잔액: %.1f원%n",
                        interest, sa.getBalance());
            }
        }
        System.out.println();

        // === 계좌 이체 테스트 === =
        try {
            System.out.println("── 계좌 이체 테스트 ──");
            bank.transfer(ac1, ac2, 5000.0);
            bank.withdraw(ac2, 5000.0); // 한도 내 출금
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bank.printAllAccounts();

        // === 예외 상황 테스트 ===
        try {
            System.out.println("예외 발생: 출금 한도 초과 테스트");
            bank.withdraw(ac2, 8000.0);
        } catch (WithdrawalLimitExceededException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("예외 발생: 잔액 부족 테스트");
            bank.withdraw(ac1, 100000.0);
        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("예외 발생: 존재하지 않는 계좌 테스트");
            bank.deposit("AC9999", 5000.0);
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}