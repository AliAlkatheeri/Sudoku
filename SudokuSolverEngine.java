// Ali Alkatheeri
// CS 143
// HW 
// This program tests the SudokuBoard class's solve functionality by attempting to
// solve Sudoku puzzles loaded from files.

public class SudokuSolverEngine {
   public static void main(String[] args) {
       // Load the puzzle
       SudokuBoard board = new SudokuBoard("boards/very-fast-solve.sdk");
       
       // Display initial state
       System.out.println("Initial board:");
       System.out.println(board);
       System.out.println();
       
       // Validate board before solving
       if (!board.isValid()) {
           System.out.println("Error: This board cannot be solved because it violates Sudoku rules.");
           return;
       }
       
       // Check if already solved
       if (board.isSolved()) {
           System.out.println("This board is already solved!");
           return;
       }
       
       // Attempt to solve
       System.out.print("Solving board...");
       long startTime = System.currentTimeMillis();
       boolean solved = board.solve();
       long endTime = System.currentTimeMillis();
       double duration = (endTime - startTime) / 1000.0;
       
       // Display results
       if (solved) {
           System.out.printf("SOLVED in %.3f seconds.\n", duration);
           System.out.println();
           System.out.println("Solution:");
           System.out.println(board);
       } else {
           System.out.println("\nError: No solution exists for this puzzle.");
       }
   }
}

/*
 ----jGRASP exec: java -ea SudokuSolverEngine
Initial board:
+-------+-------+-------+
| . 3 4 | 6 7 8 | 9 1 2 |
| . 7 2 | 1 9 5 | 3 4 8 |
| 1 9 8 | 3 4 2 | 5 6 7 |
+-------+-------+-------+
| . . 9 | . 6 1 | 4 2 3 |
| . 2 6 | 8 5 3 | 7 9 1 |
| . 1 3 | 9 2 4 | . 5 6 |
+-------+-------+-------+
| . 6 1 | 5 3 7 | 2 8 4 |
| . 8 . | 4 1 9 | 6 3 5 |
| 3 4 5 | . 8 6 | 1 7 9 |
+-------+-------+-------+


Solving board...SOLVED in 0.007 seconds.

Solution:
+-------+-------+-------+
| 5 3 4 | 6 7 8 | 9 1 2 |
| 6 7 2 | 1 9 5 | 3 4 8 |
| 1 9 8 | 3 4 2 | 5 6 7 |
+-------+-------+-------+
| 8 5 9 | 7 6 1 | 4 2 3 |
| 4 2 6 | 8 5 3 | 7 9 1 |
| 7 1 3 | 9 2 4 | 8 5 6 |
+-------+-------+-------+
| 9 6 1 | 5 3 7 | 2 8 4 |
| 2 8 7 | 4 1 9 | 6 3 5 |
| 3 4 5 | 2 8 6 | 1 7 9 |
+-------+-------+-------+


 ----jGRASP: Operation complete.

*/