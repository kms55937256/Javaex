package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // === ���� ���� ===
        String ac1 = bank.createSavingsAccount("ȫ�浿", 10000.0, 0.03);
        String ac2 = bank.createCheckingAccount("�����", 20000.0, 5000.0);
        String ac3 = bank.createSavingsAccount("�̿���", 30000.0, 0.02);

        System.out.println();
        bank.printAllAccounts();

        // === �Ա�/��� �׽�Ʈ ===
        try {
            System.out.println("���� �Ա�/��� �׽�Ʈ ����");
            bank.deposit(ac1, 5000.0);
            bank.withdraw(ac1, 3000.0);
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("���� ���� ���� �׽�Ʈ ����");
        for (Account a : bank.getAccounts()) {          
            if (a instanceof SavingsAccount) {          
                SavingsAccount sa = (SavingsAccount) a; 
                double before = sa.getBalance();
                double interest = before * sa.getInterestRate();
                sa.applyInterest();
                System.out.printf("���� %.1f���� ����Ǿ����ϴ�. ���� �ܾ�: %.1f��%n",
                        interest, sa.getBalance());
            }
        }
        System.out.println();

        // === ���� ��ü �׽�Ʈ === =
        try {
            System.out.println("���� ���� ��ü �׽�Ʈ ����");
            bank.transfer(ac1, ac2, 5000.0);
            bank.withdraw(ac2, 5000.0); // �ѵ� �� ���
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        bank.printAllAccounts();

        // === ���� ��Ȳ �׽�Ʈ ===
        try {
            System.out.println("���� �߻�: ��� �ѵ� �ʰ� �׽�Ʈ");
            bank.withdraw(ac2, 8000.0);
        } catch (WithdrawalLimitExceededException e) {
            System.out.println("���� �߻�: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("���� �߻�: �ܾ� ���� �׽�Ʈ");
            bank.withdraw(ac1, 100000.0);
        } catch (InsufficientBalanceException e) {
            System.out.println("���� �߻�: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("���� �߻�: �������� �ʴ� ���� �׽�Ʈ");
            bank.deposit("AC9999", 5000.0);
        } catch (AccountNotFoundException e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }
    }
}