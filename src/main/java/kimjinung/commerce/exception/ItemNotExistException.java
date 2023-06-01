package kimjinung.commerce.exception;

public class ItemNotExistException extends RuntimeException {
    public ItemNotExistException() {
        super("Item not found");
    }
}
