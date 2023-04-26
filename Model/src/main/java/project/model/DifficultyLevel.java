package project.model;

import java.util.Random;

public enum DifficultyLevel {
    EASY(15),
    NORMAL(30),
    HARD(45);

    public int fieldsToErase;

    DifficultyLevel(int fieldsToErase) {
        this.fieldsToErase = fieldsToErase;
    }

    public void eraseFields(final SudokuBoard sudokuBoard) {
        int settedFields = 0;
        Random random = new Random();
        while (settedFields < fieldsToErase) {
            int positionX = random.nextInt(1, 9);
            int positionY = random.nextInt(1,9);

            if (sudokuBoard.getFieldValue(positionX, positionY) != 0) {
                sudokuBoard.setFieldValue(positionX, positionY, 0);
                settedFields++;
            }
        }

    }

}
