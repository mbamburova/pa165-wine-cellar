package cz.muni.fi.pa165.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author Michaela Bamburová on 20.11.2016.
 */
public class WineCellarDataAccessException extends DataAccessException {

    public WineCellarDataAccessException(String msg) {
        super(msg);
    }

    public WineCellarDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
