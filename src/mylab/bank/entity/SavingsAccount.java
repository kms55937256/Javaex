package mylab.bank.entity;

public class SavingsAccount extends Account {
    private double interestRate; // 0.03 = 3%

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    // ���� ����
    public void applyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", ������: %.1f%%", interestRate * 100);
    }
}