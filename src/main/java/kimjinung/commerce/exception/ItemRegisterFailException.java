package kimjinung.commerce.exception;

public class ItemRegisterFailException extends RuntimeException {
    public ItemRegisterFailException() {
        super("Fail to register item");
    }
}
