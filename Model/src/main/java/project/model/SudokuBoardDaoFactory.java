package project.model;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileSudokuBoardDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}
