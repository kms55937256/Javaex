package mylab.bank.exception;

// 한도 초과는 잔액부족 예외의 특수 케이스로 모델링
public class WithdrawalLimitExceededException extends InsufficientBalanceException {
    public WithdrawalLimitExceededException(String msg) { super(msg); }
}