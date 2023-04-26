/**
 * The GNU General Public License (GPL).
 */

package project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void testFillBoard(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();

        assertEquals(true, sudokuBoard.isBoardValid());
        int fieldValue = sudokuBoard.getFieldValue(0,0);
        sudokuBoard.setFieldValue(0, 1, fieldValue);
        assertEquals(false, sudokuBoard.isBoardValid());
    }

    @Test
    public void testIsBoardsNotTheSame(){
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        assertEquals(true, sudokuBoard1.isBoardValid());
        assertEquals(true, sudokuBoard2.isBoardValid());

        assertEquals(false, sudokuBoard1.equals(sudokuBoard2));

    }

    @Test
    public void testHashCode(){
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard emptySudokuBoard = null;
        SudokuColumn sudokuColumn = sudokuBoard2.getColumn(1);

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        assertEquals(true, sudokuBoard1.equals(sudokuBoard1));
        assertEquals(false, sudokuBoard1.equals(sudokuBoard2));
        assertEquals(false, sudokuBoard1.equals(emptySudokuBoard));
        assertEquals(false, sudokuBoard1.equals(sudokuColumn));
        assertEquals(sudokuBoard1.hashCode(), sudokuBoard1.hashCode());
        assertNotEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());
    }

    @Test
    public void testToString(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();

        assertEquals(sudokuBoard.toString(), sudokuBoard.toString());
    }

    @Test
    public void sudokuBoardCloneTest() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = (SudokuBoard) sudokuBoard1.clone();
        assertTrue(sudokuBoard1.equals(sudokuBoard2));
        sudokuBoard1.solveGame();
        assertFalse(sudokuBoard1.equals(sudokuBoard2));
    }

}