/**
 * The GNU General Public License (GPL).
 */

package project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {
    @Test
    public void testVerifySudokuColumn() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        for (int i = 0; i < 9; i++) {
            SudokuColumn sudokuColumn = sudokuBoard.getColumn(i);
            assertEquals(true, sudokuColumn.verify());
        }
        int tmp = sudokuBoard.getFieldValue(0, 0);
        sudokuBoard.setFieldValue(1, 0, tmp);
        SudokuColumn sudokuColumn = sudokuBoard.getColumn(0);
        assertEquals(false, sudokuColumn.verify());
    }

    @Test
    public void testHashCode() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        SudokuColumn sudokuColumn1 = sudokuBoard.getColumn(0);
        SudokuColumn sudokuColumn2 = sudokuBoard.getColumn(1);
        SudokuColumn emptySudokuColumn = null;
        SudokuRow sudokuRow = sudokuBoard.getRow(0);

        assertEquals(sudokuColumn1.hashCode(), sudokuColumn1.hashCode());
        assertNotEquals(sudokuColumn1.hashCode(), sudokuColumn2.hashCode());
        assertEquals(true, sudokuColumn1.equals(sudokuColumn1));
        assertEquals(false, sudokuColumn1.equals(sudokuColumn2));
        assertEquals(false, sudokuColumn1.equals(emptySudokuColumn));
        assertEquals(false, sudokuColumn1.equals(sudokuRow));
    }

    @Test
    public void toStringTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        assertEquals(true, sudokuBoard.isBoardValid());

        SudokuColumn sudokuColumn = sudokuBoard.getColumn(0);

        assertEquals(sudokuColumn.toString(), sudokuColumn.toString());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        SudokuColumn sudokuColumn1 = sudokuBoard.getColumn(0);
        SudokuColumn sudokuColumn2 = (SudokuColumn)sudokuColumn1.clone();

        assertTrue(sudokuColumn1.equals(sudokuColumn2));

        int tmp = sudokuColumn1.getSudokuFieldsArray().get(1).getFieldValue();
        tmp+=1;
        sudokuColumn1.setSudokuAreaField(1, tmp);

        assertFalse(sudokuColumn1.equals(sudokuColumn2));
    }
}