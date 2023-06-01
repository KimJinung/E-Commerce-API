package kimjinung.commerce.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserIdAlreadyExistException extends RuntimeException{

    public UserIdAlreadyExistException(String message) {
        super();
        log.info("[Exception] %s already exist user id", message);
    }
}
