package mylab.bank.exception;

// �ѵ� �ʰ��� �ܾ׺��� ������ Ư�� ���̽��� �𵨸�
public class WithdrawalLimitExceededException extends InsufficientBalanceException {
    public WithdrawalLimitExceededException(String msg) { super(msg); }
}