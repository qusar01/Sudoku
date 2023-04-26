package project.model;

import org.junit.jupiter.api.Test;
import project.model.exceptions.SudokuNullPointerException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {
    @Test
    public void testHashCode() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        SudokuField sudokuField1 = new SudokuField(sudokuBoard.getFieldValue(0,0));
        SudokuField sudokuField2 = new SudokuField(sudokuBoard.getFieldValue(0,1));
        SudokuField emptySudokuField = null;
        SudokuColumn sudokuColumn = null;

        assertEquals(sudokuField1.hashCode(), sudokuField1.hashCode());
        assertNotEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
        assertTrue(sudokuField1.equals(sudokuField1));
        assertFalse(sudokuField1.equals(sudokuField2));
        assertFalse(sudokuField1.equals(emptySudokuField));
        assertFalse(sudokuField1.equals(sudokuColumn));
    }

    @Test
    public void toStringTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        SudokuField sudokuField = new SudokuField(sudokuBoard.getFieldValue(0,0));

        assertEquals(sudokuField.toString(), sudokuField.toString());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuField sudokuField = new SudokuField(2);
        SudokuField sudokuField1 = (SudokuField)sudokuField.clone();

        assertEquals(sudokuField.getFieldValue(), sudokuField1.getFieldValue());

        sudokuField1.setFieldValue(5);

        assertNotEquals(sudokuField.getFieldValue(), sudokuField1.getFieldValue());
    }

    @Test
    public  void compareToTest() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);

        assertEquals(0, sudokuField1.compareTo(sudokuField2));

        sudokuField1.setFieldValue(5);

        assertEquals(1, sudokuField1.compareTo(sudokuField2));
        assertEquals(-1, sudokuField2.compareTo(sudokuField1));

        SudokuField nullSudokuField = null;

        assertThrows(SudokuNullPointerException.class, () -> {
            sudokuField1.compareTo(nullSudokuField);
        });
    }
}
