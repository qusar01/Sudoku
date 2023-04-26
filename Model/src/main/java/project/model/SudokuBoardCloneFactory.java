package project.model;

public class SudokuBoardCloneFactory {
    public SudokuBoardCloneFactory() {
    }

    public SudokuBoard getClone(SudokuBoard sudokuBoard) throws CloneNotSupportedException {
        return (SudokuBoard) sudokuBoard.clone();
    }
}
