package kimjinung.commerce.exception;

public class MemberInfoNotMatchException extends RuntimeException{
    public MemberInfoNotMatchException() {
        super("Not match member information");
    }
}
