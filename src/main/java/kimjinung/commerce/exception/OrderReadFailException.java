package kimjinung.commerce.exception;

public class OrderReadFailException extends RuntimeException{
    public OrderReadFailException() {
        super("Fail to read order");
    }
}
