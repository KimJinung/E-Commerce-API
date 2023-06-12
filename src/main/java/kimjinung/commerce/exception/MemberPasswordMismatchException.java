package kimjinung.commerce.exception;

public class MemberPasswordMismatchException extends RuntimeException {
    public MemberPasswordMismatchException() {
        super("Password mismatch");
    }
}
