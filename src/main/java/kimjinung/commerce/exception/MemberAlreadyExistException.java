package kimjinung.commerce.exception;

public class MemberAlreadyExistException extends RuntimeException{
    public MemberAlreadyExistException() {
        super("Member already exist");
    }
}
