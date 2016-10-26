package cz.muni.fi.pa165.exceptions;

/**
 * @author Michaela Bamburová on 26.10.2016.
 */
public class WineCellarServiceException extends RuntimeException {

    public WineCellarServiceException() {
        super();
    }

    public WineCellarServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WineCellarServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WineCellarServiceException(String message) {
        super(message);
    }

    public WineCellarServiceException(Throwable cause) {
        super(cause);
    }
}
