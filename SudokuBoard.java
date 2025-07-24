// Ali Alkatheeri
// CS 143 
// HW: Sudoku Board Implementation - 2D Arrays, File I/O, Classes
//
// This program implements a simple Sudoku board class that can read a board from a file
// and display it using basic programming concepts.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuBoard {
    private int[][] board; // 2D array to store the Sudoku board (using 0 for empty cells)
    
    /**
     * Creates a new Sudoku board by reading from the specified file.
     * pre: filePath points to a valid .sdk file with 9 rows of 9 characters each
     * post: board is initialized with the contents of the file
     */
    public SudokuBoard(String filePath) {
        board = new int[9][9];
        try {
            Scanner scanner = new Scanner(new File(filePath));
            // Read each row
            for (int row = 0; row < 9; row++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // Read each character in the row
                    for (int col = 0; col < 9; col++) {
                        char cell = line.charAt(col);
                        // If it's a dot, store 0, otherwise convert char to number
                        if (cell == '.') {
                            board[row][col] = 0;
                        } else {
                            board[row][col] = cell - '0';  // Convert char to int
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + filePath);
        }
    }
    
    /**
     * Returns a string representation of the Sudoku board.
     * pre: board has been initialized
     * post: returns a simple string representation of the board
     */
    @Override
    public String toString() {
        String result = "";
        
        // Add top border
        result = result + "+-------+-------+-------+\n";
        
        // Print each row
        for (int row = 0; row < 9; row++) {
            result = result + "| ";
            // Print each number in the row
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    result = result + ". ";
                } else {
                    result = result + board[row][col] + " ";
                }
                // Add vertical lines between 3x3 boxes
                if (col == 2 || col == 5) {
                    result = result + "| ";
                }
            }
            result = result + "|\n";
            
            // Add horizontal lines between 3x3 boxes
            if (row == 2 || row == 5 || row == 8) {
                result = result + "+-------+-------+-------+\n";
            }
        }
        
        return result;
    }
}