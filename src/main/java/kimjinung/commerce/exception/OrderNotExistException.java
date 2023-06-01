package kimjinung.commerce.exception;

public class OrderNotExistException extends RuntimeException
{
    public OrderNotExistException() {
        super("Order not exist");
    }
}
