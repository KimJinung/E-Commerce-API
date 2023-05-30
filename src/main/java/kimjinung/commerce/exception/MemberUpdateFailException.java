package kimjinung.commerce.exception;

public class MemberUpdateFailException extends RuntimeException{

    public MemberUpdateFailException() {
        super("Member update fail");
    }
}
