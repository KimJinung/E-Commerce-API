package kimjinung.commerce.controller.exhandler;

import kimjinung.commerce.dto.common.ResponseDto;
import kimjinung.commerce.dto.error.Error;
import kimjinung.commerce.dto.error.ErrorResult;
import kimjinung.commerce.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberPasswordMismatchException.class)
    public ResponseDto<ErrorResult> invalidPasswordException(MemberPasswordMismatchException ex) {
        return baseException(400, Error.INVALID_PASSWORD, ex);
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

}
