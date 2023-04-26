package project.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.model.exceptions.FileSudokuBoardDaoException;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    String filename;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    FileInputStream fis;
    FileOutputStream fos;
    ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    Locale locale = new Locale("en");
    Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws FileSudokuBoardDaoException {
        SudokuBoard obj = null;
        try {
             fis = new FileInputStream(filename);
             objectInputStream = new ObjectInputStream(fis);
             obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            logger.error("Nie znaleziono klasy!");
            throw new FileSudokuBoardDaoException(bundle.getString("ClassNotFoundMessage"));
        } catch (IOException e) {
            logger.error("Blad odczytu!");
            throw new FileSudokuBoardDaoException(bundle.getString("ReadMessage"));
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard obj) throws FileSudokuBoardDaoException {
        try {
             fos = new FileOutputStream(filename);
             objectOutputStream = new ObjectOutputStream(fos);
             objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            logger.error("Blad zapisu!");
            throw new FileSudokuBoardDaoException(bundle.getString("WriteMessage") + e);
        }
    }

    @Override
    public void close() throws Exception {
        fis.close();
        objectInputStream.close();
        fos.close();
        objectOutputStream.close();
    }
}
