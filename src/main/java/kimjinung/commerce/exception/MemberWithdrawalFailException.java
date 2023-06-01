package kimjinung.commerce.exception;

public class MemberWithdrawalFailException extends RuntimeException {
    public MemberWithdrawalFailException() {
        super("Fail to withdraw");
    }
}
