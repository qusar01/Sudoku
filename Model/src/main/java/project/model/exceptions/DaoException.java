package project.model.exceptions;

import java.io.IOException;

public class DaoException extends IOException {
    public DaoException(String message) {
        super(message);
    }
}
