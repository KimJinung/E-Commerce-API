package kimjinung.commerce.exception;

public class MemberJoinFailException extends RuntimeException{
    public MemberJoinFailException() {
        super("Fail to join");
    }
}
