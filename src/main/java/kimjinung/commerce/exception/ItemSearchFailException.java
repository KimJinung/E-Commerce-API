package kimjinung.commerce.exception;

public class ItemSearchFailException extends RuntimeException {
    public ItemSearchFailException() {
        super("Fail to search item");
    }
}
