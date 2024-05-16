package cl.govegan.msauthuser.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseStatusException handleDuplicateUsernameException(DuplicateUsernameException ex) {
        return new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
    }
}
