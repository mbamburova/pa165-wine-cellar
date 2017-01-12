package cz.muni.fi.pa165.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Silvia Borzová
 *         1/12/2017
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class SecurityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SecurityException() {}

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(Throwable cause) {
        super(cause);
    }
}
