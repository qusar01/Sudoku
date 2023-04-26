package project.model;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.model.exceptions.BadSudokuAreaSizeException;

public class SudokuArea implements Cloneable {
    private int size = 9;
    private List<SudokuField> sudokuFieldsArray;

    ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    Locale locale = new Locale("en");
    Logger logger = LoggerFactory.getLogger(SudokuArea.class);

    public SudokuArea(final List<SudokuField> fields) {
        if (fields.size() != this.size) {
            logger.error("Bledny rozmiar listy dla SudokuArea");
            throw new BadSudokuAreaSizeException(bundle.getString("SudokuAreaSizeMessage"));
        }
        this.sudokuFieldsArray = fields;
    }

    public List<SudokuField> getSudokuFieldsArray() {
        return Collections.unmodifiableList(sudokuFieldsArray);
    }

    public boolean verify() {
        for (int i = 1; i <= 9; i++) {
            int tmp = 0;
            for (int j = 0; j < size; j++) {
                if (sudokuFieldsArray.get(j).getFieldValue() == i) {
                    tmp++;
                }
            }
            if (tmp > 1) {
                return false;
            }
        }
        return true;
    }

    public void setSudokuAreaField(int positionIndex, int value) {

        this.sudokuFieldsArray.get(positionIndex).setFieldValue(value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(sudokuFieldsArray)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuArea that = (SudokuArea) o;
        return new EqualsBuilder()
                .append(size, that.size)
                .append(sudokuFieldsArray, that.sudokuFieldsArray)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(9,9)
                .append(size)
                .append(sudokuFieldsArray)
                .toHashCode();
    }


}