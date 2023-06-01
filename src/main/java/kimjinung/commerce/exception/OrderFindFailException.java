package kimjinung.commerce.exception;

public class OrderFindFailException extends RuntimeException{
    public OrderFindFailException() {
        super("Fail to read order");
    }
}
