package project.view;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.function.UnaryOperator;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.model.Dao;
import project.model.SudokuBoard;
import project.model.SudokuBoardDaoFactory;
import project.model.exceptions.DatabaseException;
import project.model.exceptions.FileSudokuBoardDaoException;


public class SecondaryController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField saveField;

    @FXML
    private TextField tf00;
    @FXML
    private TextField tf01;
    @FXML
    private TextField tf02;
    @FXML
    private TextField tf03;
    @FXML
    private TextField tf04;
    @FXML
    private TextField tf05;
    @FXML
    private TextField tf06;
    @FXML
    private TextField tf07;
    @FXML
    private TextField tf08;
    @FXML
    private TextField tf10;
    @FXML
    private TextField tf11;
    @FXML
    private TextField tf12;
    @FXML
    private TextField tf13;
    @FXML
    private TextField tf14;
    @FXML
    private TextField tf15;
    @FXML
    private TextField tf16;
    @FXML
    private TextField tf17;
    @FXML
    private TextField tf18;
    @FXML
    private TextField tf20;
    @FXML
    private TextField tf21;
    @FXML
    private TextField tf22;
    @FXML
    private TextField tf23;
    @FXML
    private TextField tf24;
    @FXML
    private TextField tf25;
    @FXML
    private TextField tf26;
    @FXML
    private TextField tf27;
    @FXML
    private TextField tf28;
    @FXML
    private TextField tf30;
    @FXML
    private TextField tf31;
    @FXML
    private TextField tf32;
    @FXML
    private TextField tf33;
    @FXML
    private TextField tf34;
    @FXML
    private TextField tf35;
    @FXML
    private TextField tf36;
    @FXML
    private TextField tf37;
    @FXML
    private TextField tf38;
    @FXML
    private TextField tf40;
    @FXML
    private TextField tf41;
    @FXML
    private TextField tf42;
    @FXML
    private TextField tf43;
    @FXML
    private TextField tf44;
    @FXML
    private TextField tf45;
    @FXML
    private TextField tf46;
    @FXML
    private TextField tf47;
    @FXML
    private TextField tf48;
    @FXML
    private TextField tf50;
    @FXML
    private TextField tf51;
    @FXML
    private TextField tf52;
    @FXML
    private TextField tf53;
    @FXML
    private TextField tf54;
    @FXML
    private TextField tf55;
    @FXML
    private TextField tf56;
    @FXML
    private TextField tf57;
    @FXML
    private TextField tf58;
    @FXML
    private TextField tf60;
    @FXML
    private TextField tf61;
    @FXML
    private TextField tf62;
    @FXML
    private TextField tf63;
    @FXML
    private TextField tf64;
    @FXML
    private TextField tf65;
    @FXML
    private TextField tf66;
    @FXML
    private TextField tf67;
    @FXML
    private TextField tf68;
    @FXML
    private TextField tf70;
    @FXML
    private TextField tf71;
    @FXML
    private TextField tf72;
    @FXML
    private TextField tf73;
    @FXML
    private TextField tf74;
    @FXML
    private TextField tf75;
    @FXML
    private TextField tf76;
    @FXML
    private TextField tf77;
    @FXML
    private TextField tf78;
    @FXML
    private TextField tf80;
    @FXML
    private TextField tf81;
    @FXML
    private TextField tf82;
    @FXML
    private TextField tf83;
    @FXML
    private TextField tf84;
    @FXML
    private TextField tf85;
    @FXML
    private TextField tf86;
    @FXML
    private TextField tf87;
    @FXML
    private TextField tf88;

    private Vector<TextField> textFields = new Vector<>();

    public void addTextFields() {
        textFields.add(tf00);
        textFields.add(tf10);
        textFields.add(tf20);
        textFields.add(tf30);
        textFields.add(tf40);
        textFields.add(tf50);
        textFields.add(tf60);
        textFields.add(tf70);
        textFields.add(tf80);
        textFields.add(tf01);
        textFields.add(tf11);
        textFields.add(tf21);
        textFields.add(tf31);
        textFields.add(tf41);
        textFields.add(tf51);
        textFields.add(tf61);
        textFields.add(tf71);
        textFields.add(tf81);
        textFields.add(tf02);
        textFields.add(tf12);
        textFields.add(tf22);
        textFields.add(tf32);
        textFields.add(tf42);
        textFields.add(tf52);
        textFields.add(tf62);
        textFields.add(tf72);
        textFields.add(tf82);
        textFields.add(tf03);
        textFields.add(tf13);
        textFields.add(tf23);
        textFields.add(tf33);
        textFields.add(tf43);
        textFields.add(tf53);
        textFields.add(tf63);
        textFields.add(tf73);
        textFields.add(tf83);
        textFields.add(tf04);
        textFields.add(tf14);
        textFields.add(tf24);
        textFields.add(tf34);
        textFields.add(tf44);
        textFields.add(tf54);
        textFields.add(tf64);
        textFields.add(tf74);
        textFields.add(tf84);
        textFields.add(tf05);
        textFields.add(tf15);
        textFields.add(tf25);
        textFields.add(tf35);
        textFields.add(tf45);
        textFields.add(tf55);
        textFields.add(tf65);
        textFields.add(tf75);
        textFields.add(tf85);
        textFields.add(tf06);
        textFields.add(tf16);
        textFields.add(tf26);
        textFields.add(tf36);
        textFields.add(tf46);
        textFields.add(tf56);
        textFields.add(tf66);
        textFields.add(tf76);
        textFields.add(tf86);
        textFields.add(tf07);
        textFields.add(tf17);
        textFields.add(tf27);
        textFields.add(tf37);
        textFields.add(tf47);
        textFields.add(tf57);
        textFields.add(tf67);
        textFields.add(tf77);
        textFields.add(tf87);
        textFields.add(tf08);
        textFields.add(tf18);
        textFields.add(tf28);
        textFields.add(tf38);
        textFields.add(tf48);
        textFields.add(tf58);
        textFields.add(tf68);
        textFields.add(tf78);
        textFields.add(tf88);
    }

    UnaryOperator<TextFormatter.Change> filter = change -> {
        String text = change.getText();

        if (text.matches("[1-9]*") && change.getControlNewText().length() <= 1)  {
            return change;
        }

        return null;
    };

    static Logger logger = LoggerFactory.getLogger(SecondaryController.class);
    SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
    Dao<SudokuBoard> fileSudokuBoardDao;
    SudokuBoard sudokuBoard;

    @FXML
    void saveSudoku(ActionEvent event) throws FileSudokuBoardDaoException, DatabaseException {
       fileSudokuBoardDao = sudokuBoardDaoFactory.getDatabaseDao(saveField.getText());
       fileSudokuBoardDao.write(sudokuBoard);
       logger.info("Zapisano gre");
    }


    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "bundles.messages", PrimaryController.location);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"), bundle);
        App.setRoot(loader);
        PrimaryController pc = loader.getController();
        App.setStart(PrimaryController.location);
    }

    public void draw(SudokuBoard sudokuBoard) throws NoSuchMethodException {
        addTextFields();
        this.sudokuBoard = sudokuBoard;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JavaBeanIntegerProperty pr = JavaBeanIntegerPropertyBuilder.create()
                        .bean(this.sudokuBoard.getField(i, j))
                        .name("fieldValue").build();
                StringConverter<Number> converter = new NumberStringConverter();
                textFields.get(i * 9 + j).textProperty().bindBidirectional(pr, converter);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getFieldValue(i, j) == 0) {
                    textFields.get(i * 9 + j).setText("");
                    TextFormatter<String> textFormatter = new TextFormatter<>(filter);
                    textFields.get(i * 9 + j).setTextFormatter(textFormatter);
                }
            }
        }
    }
}

