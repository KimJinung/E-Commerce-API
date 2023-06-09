package kimjinung.commerce.controller.api;

import kimjinung.commerce.exception.InvalidRequestException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class BaseApiController {
    protected void validateRequest(BindingResult bindingResult) throws InvalidRequestException {
        ArrayList<String> errors = new ArrayList<>();

        if (bindingResult.hasGlobalErrors()) {
            errors.addAll(bindingResult.getGlobalErrors()
                    .stream()
                    .map(error -> makeErrorMessage(error.getObjectName(), error.getDefaultMessage()))
                    .collect(Collectors.toList()));
        }

        if (bindingResult.hasFieldErrors()) {
            errors.addAll(bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> makeErrorMessage(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList()));
        }

        if (!errors.isEmpty()) {
            throw new InvalidRequestException(errors.toString());
        }
    }

    private static String makeErrorMessage(String field, String msg) {
        return String.format("[%s]= %s", field, msg);
    }
}
