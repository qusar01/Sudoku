package project.model.exceptions;

public class BadSudokuAreaSizeException extends IllegalArgumentException {
    public BadSudokuAreaSizeException(final String message) {
        super(message);
    }
}
