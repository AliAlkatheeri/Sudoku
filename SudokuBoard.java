// Ali ALktheeri
// CS 143 
// HW 
// This program implements a Sudoku board class that can read a board from a file,
// validate the board according to Sudoku rules, and check if the puzzle is solved.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
     * Checks if the current board state is valid according to Sudoku rules.
     * pre: board has been initialized
     * post: returns true if board follows all Sudoku constraints, false otherwise
     */
    public boolean isValid() {
        return hasValidData() && hasValidRows() && hasValidColumns() && hasValidMiniSquares();
    }
    
    /**
     * Checks if all data in the board is valid (1-9 or empty).
     * pre: board has been initialized
     * post: returns true if all values are valid, false otherwise
     */
    private boolean hasValidData() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board[row][col];
                if (value < 0 || value > 9) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Checks if all rows contain no duplicate values (excluding empty cells).
     * pre: board has been initialized
     * post: returns true if no row contains duplicates, false otherwise
     */
    private boolean hasValidRows() {
        for (int row = 0; row < 9; row++) {
            Set<Integer> seen = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                int value = board[row][col];
                if (value != 0) {
                    if (seen.contains(value)) {
                        return false;
                    }
                    seen.add(value);
                }
            }
        }
        return true;
    }
    
    /**
     * Checks if all columns contain no duplicate values (excluding empty cells).
     * pre: board has been initialized
     * post: returns true if no column contains duplicates, false otherwise
     */
    private boolean hasValidColumns() {
        for (int col = 0; col < 9; col++) {
            Set<Integer> seen = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                int value = board[row][col];
                if (value != 0) {
                    if (seen.contains(value)) {
                        return false;
                    }
                    seen.add(value);
                }
            }
        }
        return true;
    }
    
    /**
     * Helper method to get a 3x3 mini-square from the board.
     * pre: spot is between 1 and 9
     * post: returns a 3x3 array containing the specified mini-square
     */
    private int[][] miniSquare(int spot) {
        int[][] mini = new int[3][3];
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
            }
        }
        return mini;
    }
    
    /**
     * Checks if all 3x3 mini-squares contain no duplicate values (excluding empty cells).
     * pre: board has been initialized
     * post: returns true if no mini-square contains duplicates, false otherwise
     */
    private boolean hasValidMiniSquares() {
        for (int square = 1; square <= 9; square++) {
            int[][] mini = miniSquare(square);
            Set<Integer> seen = new HashSet<>();
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int value = mini[row][col];
                    if (value != 0) {
                        if (seen.contains(value)) {
                            return false;
                        }
                        seen.add(value);
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * Checks if the board is completely solved.
     * pre: board has been initialized
     * post: returns true if the board is valid and complete, false otherwise
     */
 public boolean isSolved() {
        if (!isValid()) {
            return false;
        }
        
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            frequencies.put(i, 0);
        }
        
        // Count frequencies of each number
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board[row][col];
                if (value == 0) {
                    return false; // Board has empty cells
                }
                frequencies.put(value, frequencies.get(value) + 1);
            }
        }
        
        // Check if each number appears exactly 9 times
        for (int count : frequencies.values()) {
            if (count != 9) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Returns a string representation of the Sudoku board.
     * pre: board has been initialized
     * post: returns a string representation of the board
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