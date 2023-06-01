package kimjinung.commerce.controller.api.error;

import lombok.Data;

@Data
public class ErrorResult {
    private String error;
    private String message;

    public ErrorResult(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
