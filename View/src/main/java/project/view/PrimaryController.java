package project.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.model.BacktrackingSudokuSolver;
import project.model.Dao;
import project.model.DifficultyLevel;
import project.model.SudokuBoard;
import project.model.SudokuBoardDaoFactory;
import project.model.exceptions.DatabaseException;
import project.model.exceptions.SudokuNullPointerException;


public class PrimaryController {

    @FXML
    private RadioButton button1;
    @FXML
    private RadioButton button2;
    @FXML
    private RadioButton button3;
    @FXML
    private TextField loadField;
    static Logger logger = LoggerFactory.getLogger(PrimaryController.class);

    DifficultyLevel difficultyLevel = null;
    SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
    Dao<SudokuBoard> fileSudokuBoardDao;

    static Locale location = new Locale("en");

    @FXML
    void changeLang(ActionEvent event) throws IOException {
        if (PrimaryController.location.getLanguage() == "en") {
            location = new Locale("pl");
        } else {
            location = new Locale("en");
        }
        App.setStart(location);
    }

    @FXML
    void setDifficulty(ActionEvent event) throws IOException {
        if (button1.isSelected()) {
            difficultyLevel = DifficultyLevel.EASY;
            button2.setDisable(true);
            button3.setDisable(true);
        } else if (button2.isSelected()) {
            difficultyLevel = DifficultyLevel.NORMAL;
            button1.setDisable(true);
            button3.setDisable(true);
        } else if (button3.isSelected()) {
            difficultyLevel = DifficultyLevel.HARD;
            button1.setDisable(true);
            button2.setDisable(true);
        } else if (!button1.isSelected()) {
            difficultyLevel = null;
            button1.setDisable(false);
            button2.setDisable(false);
            button3.setDisable(false);
        } else if (!button2.isSelected()) {
            difficultyLevel = null;
            button1.setDisable(false);
            button2.setDisable(false);
            button3.setDisable(false);
        } else if (!button3.isSelected()) {
            difficultyLevel = null;
            button1.setDisable(false);
            button2.setDisable(false);
            button3.setDisable(false);
        }
    }

    @FXML
    void loadSudoku(ActionEvent event) throws NoSuchMethodException,
            IOException, DatabaseException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages", location);
        SudokuBoard sudokuBoard;
        fileSudokuBoardDao = sudokuBoardDaoFactory.getDatabaseDao(loadField.getText());
        sudokuBoard = fileSudokuBoardDao.read();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"), bundle);
        App.setRoot(loader);
        SecondaryController sc = loader.getController();
        sc.draw(sudokuBoard);
        logger.info("Wczytano gre");
    }

    @FXML
    private void play() throws IOException, NoSuchMethodException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages", location);

        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();

        try {
            difficultyLevel.eraseFields(sudokuBoard);
        } catch (NullPointerException e) {
            logger.error("Nie wybrano poziomu trudnosci");
            throw new SudokuNullPointerException("");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"), bundle);
        App.setRoot(loader);
        SecondaryController sc = loader.getController();
        sc.draw(sudokuBoard);
        switch (difficultyLevel) {
            case EASY:
                logger.info("Rozpoczeto gre na latwym");
                break;
            case NORMAL:
                logger.info("Rozpoczeto gre na normalnym");
                break;
            case HARD:
                logger.info("Rozpoczeto gre na trudnym");
                break;
            default:
                break;
        }
    }

}
