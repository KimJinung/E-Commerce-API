package kimjinung.commerce.exception;

public class ItemUpdateFailException extends RuntimeException {
    public ItemUpdateFailException() {
        super("Fail to update item");
    }
}
