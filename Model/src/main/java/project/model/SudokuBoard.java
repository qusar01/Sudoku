/**
 * The GNU General Public License (GPL).
 */

package project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {
    static int BOARD_SIZE = 9;
    private List<SudokuField> board = Arrays.asList(new SudokuField[81]);
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
                    board.set(i, new SudokuField(0));
        }
        this.sudokuSolver = sudokuSolver;

    }

    public SudokuField getField(int x, int y) {
        return board.get(x * 9 + y);
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> fields = new ArrayList<>(9);
        for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
            fields.add(new SudokuField(getFieldValue(y, columnIndex)));
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = new ArrayList<>(9);
        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            fields.add(new SudokuField(getFieldValue(rowIndex, x)));
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = new ArrayList<>(9);
        int localBoxRowIndex = x - (x % 3);
        int localBoxColumnIndex = y - (y % 3);

        for (int row = localBoxRowIndex; row < localBoxRowIndex + 3; row++) {
            for (int column = localBoxColumnIndex;
                 column < localBoxColumnIndex + 3; column++) {
                fields.add(new SudokuField(getFieldValue(row, column)));
            }
        }
        return new SudokuBox(fields);
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    private boolean isColumnValid(int rowIndex, int columnIndex, int numberToInsert) {
        SudokuColumn sudokuColumn = getColumn(columnIndex);
        sudokuColumn.setSudokuAreaField(rowIndex, numberToInsert);

        if (sudokuColumn.verify()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isRowValid(int rowIndex, int columnIndex, int numberToInsert) {
        SudokuRow sudokuRow = getRow(rowIndex);
        sudokuRow.setSudokuAreaField(columnIndex, numberToInsert);

        if (sudokuRow.verify()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isBoxValid(int rowIndex, int columnIndex, int numberToInsert) {
        SudokuBox sudokuBox = getBox(rowIndex, columnIndex);
        int boxRowIndex = rowIndex % 3;
        int boxColumnIndex = columnIndex  % 3;
        int placeToInsertValue = 3 * boxRowIndex + boxColumnIndex;
        sudokuBox.setSudokuAreaField(placeToInsertValue, numberToInsert);

        if (sudokuBox.verify()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBoardValid() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {

                int tmp = getFieldValue(row, column);
                setFieldValue(row, column, 0);

                if (!isNumberValid(row, column, tmp)) {
                    return false;
                }
                setFieldValue(row, column, tmp);
            }
        }
        return true;
    }

    public boolean isNumberValid(int rowIndex, int columnIndex, int numberToInsert) {
        if (isColumnValid(rowIndex, columnIndex, numberToInsert)
                && isRowValid(rowIndex, columnIndex, numberToInsert)
                && isBoxValid(rowIndex, columnIndex, numberToInsert)) {
            return true;
        }
        return false;
    }

    public int getFieldValue(int x, int y) {
        int positionInList = 9 * x + y;
        int tmp = board.get(positionInList).getFieldValue();
        return tmp;
    }

    public void setFieldValue(int x, int y, int value) {
        int positionInList = 9 * x + y;
        board.get(positionInList).setFieldValue(value);
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append(board).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return new EqualsBuilder()
                .append(BOARD_SIZE, that.BOARD_SIZE)
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(9,9)
                .append(BOARD_SIZE)
                .append(board)
                .toHashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp = this.getFieldValue(i, j);
                sudokuBoard.setFieldValue(i, j, tmp);
            }
        }
        return sudokuBoard;
    }
}