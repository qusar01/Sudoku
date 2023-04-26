package project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import project.model.exceptions.DatabaseException;
import project.model.exceptions.FileSudokuBoardDaoException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    private String filename;
    private String boardsNamesDb = "boards_names";
    private String fieldsDb = "fields";
    private String jdbcUrl = "jdbc:sqlite:./SudokuDB";

    public JdbcSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws FileSudokuBoardDaoException, DatabaseException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        Connection connection = prepareConnection(jdbcUrl);
        ResultSet resultSet;
        String selectTableID = "SELECT tableID FROM " + boardsNamesDb + " WHERE tableName = '"
                + filename + "';";
        String tableName = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectTableID)) {
            resultSet = preparedStatement.executeQuery();
            tableName = resultSet.getString(1);
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldValue = "";
                String selectFieldValue = "SELECT fieldValue FROM " + fieldsDb
                        + " WHERE fieldX = ? AND fieldY = ? AND tableID = " + tableName;
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        selectFieldValue)) {
                    preparedStatement.setString(1, String.valueOf(i));
                    preparedStatement.setString(2, String.valueOf(j));
                    resultSet = preparedStatement.executeQuery();
                    fieldValue = resultSet.getString(1);
                    sudokuBoard.setFieldValue(i, j, Integer.valueOf(fieldValue));
                } catch (SQLException e) {
                    throw new DatabaseException(e.toString());
                }
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws FileSudokuBoardDaoException, DatabaseException {
        String createBoardsNamesTable = "create table " + boardsNamesDb
                + " (tableID INTEGER primary key autoincrement, "
                + "tableName varchar(50))";

        String createFieldsTable = "create table " + fieldsDb
                + " (fieldID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "fieldX INTEGER NOT NULL, "
                + "fieldY INTEGER NOT NULL, "
                + "fieldValue INTEGER NOT NULL, "
                + "tableID INTEGER NOT NULL,"
                + "FOREIGN KEY (tableID) REFERENCES " + boardsNamesDb + " (tableID))";

        Connection connection = prepareConnection(jdbcUrl);

        if (!checkIfTableExist(boardsNamesDb)) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(createBoardsNamesTable);
                if (!checkIfTableExist(fieldsDb)) {
                    statement.execute(createFieldsTable);
                }
            } catch (SQLException e) {
                throw new DatabaseException(e.toString());
            }
        }

        String insertDataIntoBoardsNamesTable = "insert into boards_names(tableName) values (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                insertDataIntoBoardsNamesTable)) {
            preparedStatement.setString(1, filename);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }

        String tableId = "";
        String findTable = "SELECT tableID FROM " + boardsNamesDb + " WHERE tableName = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findTable)) {
            preparedStatement.setString(1, filename);
            ResultSet resultSet = preparedStatement.executeQuery();
            tableId = resultSet.getString(1);
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String insertDataIntoFieldsTable = "insert into fields("
                        + "fieldX, fieldY, fieldValue, tableID) values"
                        + " (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        insertDataIntoFieldsTable)) {
                    preparedStatement.setString(1, String.valueOf(i));
                    preparedStatement.setString(2, String.valueOf(j));
                    preparedStatement.setString(3, String.valueOf(obj.getFieldValue(i, j)));
                    preparedStatement.setString(4, tableId);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new DatabaseException(e.toString());
                }
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }

    }

    private Connection prepareConnection(String jdbcUrl) throws DatabaseException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }

        return connection;
    }

    public boolean checkIfTableExist(String tableName) throws DatabaseException {
        Connection connection = prepareConnection(jdbcUrl);
        String findTable = "SELECT * FROM " + tableName + ";";
        try {
            connection.prepareStatement(findTable);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
