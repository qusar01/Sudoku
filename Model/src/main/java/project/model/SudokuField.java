/**
 * The GNU General Public License (GPL).
 */

package project.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.model.exceptions.SudokuNullPointerException;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value = 0;


    Locale locale = new Locale("en");
    Logger logger = LoggerFactory.getLogger(SudokuField.class);

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append(value).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return new EqualsBuilder()
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(9, 9)
                .append(value)
                .toHashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(SudokuField o) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        if (o == null) {
            logger.error("Porownano z wskaznikiem na NULL");
            throw new SudokuNullPointerException(bundle.getString("NullPointerMessage"));
        } else if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
