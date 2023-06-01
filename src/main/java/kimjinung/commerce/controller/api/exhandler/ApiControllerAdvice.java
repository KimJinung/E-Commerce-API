package kimjinung.commerce.controller.api.exhandler;

import kimjinung.commerce.dto.error.ErrorResult;
import kimjinung.commerce.exception.InvalidRequestException;
import kimjinung.commerce.exception.MemberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public ErrorResult memberNotFoundException(MemberNotFoundException e) {
        log.info("[Exception] ", e);
        return new ErrorResult("NOT_FOUND", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ErrorResult invalidRequestException(InvalidRequestException e) {
        log.info("[Exception ", e);
        return new ErrorResult("INVALID_ARGUMENT", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResult runtimeException(RuntimeException e) {
        log.info("[Exception] ", e);
        return new ErrorResult("BAD_REQUEST", e.getMessage());
    }

}
