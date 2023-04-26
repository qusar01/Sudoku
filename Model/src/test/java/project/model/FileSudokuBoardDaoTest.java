package project.model;

import org.junit.jupiter.api.Test;
import project.model.exceptions.DatabaseException;
import project.model.exceptions.FileSudokuBoardDaoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest() throws FileSudokuBoardDaoException, DatabaseException {
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard1.solveGame();

        Dao<SudokuBoard> fileSudokuBoardDao;

        fileSudokuBoardDao = sudokuBoardDaoFactory.getFileSudokuBoardDao("save.txt");
        fileSudokuBoardDao.write(sudokuBoard1);
        SudokuBoard sudokuBoard2 = fileSudokuBoardDao.read();
        assertEquals(sudokuBoard1, sudokuBoard2);
    }

    @Test
    public void readExceptionTest(){
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao;

        fileSudokuBoardDao = factory.getFileSudokuBoardDao("test.txt");

       assertThrows(FileSudokuBoardDaoException.class, () -> {
            fileSudokuBoardDao.read();
        });

    }

    @Test
    public void writeExceptionTest(){
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao;
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        fileSudokuBoardDao = sudokuBoardDaoFactory.getFileSudokuBoardDao("<?>***");

        assertThrows(FileSudokuBoardDaoException.class, () -> {
            fileSudokuBoardDao.write(sudokuBoard);
        });


    }

}