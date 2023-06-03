package kimjinung.commerce.controller.exhandler;

import kimjinung.commerce.dto.common.ResponseDto;
import kimjinung.commerce.dto.error.Error;
import kimjinung.commerce.dto.error.ErrorResult;
import kimjinung.commerce.exception.InvalidRequestException;
import kimjinung.commerce.exception.ItemNotFoundException;
import kimjinung.commerce.exception.ItemRegisterFailException;
import kimjinung.commerce.exception.MemberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseDto<ErrorResult> runtimeException(RuntimeException ex) {
        return baseException(400, Error.SERVER_ERROR, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseDto<ErrorResult> invalidRequestException(InvalidRequestException ex) {
        return baseException(400, Error.INVALID_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseDto<ErrorResult> memberNotFoundException(MemberNotFoundException ex) {
        return baseException(404, Error.NOT_FOUND, ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseDto<ErrorResult> itemNotFoundException(ItemNotFoundException ex) {
        return baseException(404, Error.NOT_FOUND, ex);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ItemRegisterFailException.class)
    public ResponseDto<ErrorResult> itemRegisterFailException(ItemRegisterFailException ex) {
        return baseException(500, Error.SERVER_ERROR, ex);
    }

    private ResponseDto<ErrorResult> baseException(int statusCode, Error error, RuntimeException exception) {
        log.info(exception.getMessage());
        ErrorResult errorResult = new ErrorResult(error.toString(), exception.getMessage());
        return new ResponseDto<>(statusCode, errorResult);
    }

    public static void validateRequest(BindingResult bindingResult) throws InvalidRequestException {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasGlobalErrors()) {
                List<String> errors = bindingResult.getGlobalErrors()
                        .stream()
                        .map(error -> makeErrorMessage(error.getObjectName(), error.getDefaultMessage()) )
                        .collect(Collectors.toList());
                throw new InvalidRequestException(errors.toString());
            }
            if (bindingResult.hasFieldErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(error -> makeErrorMessage(error.getField(), error.getDefaultMessage()))
                        .collect(Collectors.toList());
                throw new InvalidRequestException(errors.toString());
            }
        }
    }

    private static String makeErrorMessage(String field, String msg) {
        return String.format("[%s]= %s", field, msg);
    }
}
