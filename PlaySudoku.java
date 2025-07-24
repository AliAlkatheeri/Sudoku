// Ali Alkatheeri
// CS 143
// HW: Sudoku Board Implementation - Main Program
//
// This program tests the SudokuBoard class by creating a board from a file
// and displaying it.

public class PlaySudoku {
    public static void main(String[] args) {
        // Creating a new SudokuBoard object with the specified file
        SudokuBoard board = new SudokuBoard("data1.sdk");
        System.out.println(board);
    }
}

/*
 * Program Output (when run with the provided data1.sdk file):
 * +-------+-------+-------+
 * | 2 . . | 1 . 5 | . . 3 |
 * | . 5 4 | . . . | 7 1 . |
 * | . 1 . | 2 . 3 | . 8 . |
 * +-------+-------+-------+
 * | 6 . 2 | 8 . 7 | 3 . 4 |
 * | . . . | . . . | . . . |
 * | 1 . 5 | 3 . 9 | 8 . 6 |
 * +-------+-------+-------+
 * | . 2 . | 7 . 1 | . 6 . |
 * | . 8 1 | . . . | 2 4 . |
 * | 7 . . | 4 . 2 | . . 1 |
 * +-------+-------+-------+
 * 
 */