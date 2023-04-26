/**
 * The GNU General Public License (GPL).
 */

package project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {
    @Test
    public void testVerifySudokuRow() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        for (int i = 0; i < 9; i++) {
            SudokuRow sudokuRow = sudokuBoard.getRow(i);
            assertEquals(true, sudokuRow.verify());
        }
        int tmp = sudokuBoard.getFieldValue(0, 0);
        sudokuBoard.setFieldValue(0, 1, tmp);
        SudokuRow sudokuRow = sudokuBoard.getRow(0);
        assertEquals(false, sudokuRow.verify());
    }

    @Test
    public void testHashCode() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        SudokuRow sudokuRow1 = sudokuBoard.getRow(0);
        SudokuRow sudokuRow2 = sudokuBoard.getRow(1);
        SudokuRow emptySudokuRow = null;
        SudokuColumn sudokuColumn = sudokuBoard.getColumn(0);

        assertEquals(sudokuRow1.hashCode(), sudokuRow1.hashCode());
        assertNotEquals(sudokuRow1.hashCode(), sudokuRow2.hashCode());
        assertEquals(true, sudokuRow1.equals(sudokuRow1));
        assertEquals(false, sudokuRow1.equals(sudokuRow2));
        assertEquals(false, sudokuRow1.equals(emptySudokuRow));
        assertEquals(false, sudokuRow1.equals(sudokuColumn));
    }

    @Test
    public void toStringTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        SudokuRow sudokuRow = sudokuBoard.getRow(0);

        assertEquals(sudokuRow.toString(), sudokuRow.toString());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        SudokuRow sudokuRow1 = sudokuBoard.getRow(0);
        SudokuRow sudokuRow2 = (SudokuRow)sudokuRow1.clone();

        assertTrue(sudokuRow1.equals(sudokuRow2));

        int tmp = sudokuRow1.getSudokuFieldsArray().get(1).getFieldValue();
        tmp+=1;
        sudokuRow1.setSudokuAreaField(1, tmp);

        assertFalse(sudokuRow1.equals(sudokuRow2));
    }
}