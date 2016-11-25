package cz.muni.fi.pa165.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author MarekScholtz
 * @version 2016.11.25
 */
public class WineCellarDataAccesException extends DataAccessException {

    public WineCellarDataAccesException(String msg) {
        super(msg);
    }

    public WineCellarDataAccesException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
