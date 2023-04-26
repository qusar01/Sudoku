package project.model;

import project.model.exceptions.DatabaseException;
import project.model.exceptions.FileSudokuBoardDaoException;

public interface Dao<T> {
    T read() throws FileSudokuBoardDaoException, DatabaseException;

    void write(T obj) throws FileSudokuBoardDaoException, DatabaseException;
}
