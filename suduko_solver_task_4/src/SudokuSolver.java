import java.util.Scanner;

public class SudokuSolver {

    static final int SIZE = 9;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];

        System.out.println("Enter the Sudoku grid (use 0 for empty cells):");

        for (int i = 0; i < SIZE; i++) {
            System.out.println("Enter row " + (i + 1) + ":");
            for (int j = 0; j < SIZE; j++) {
                while (true) {
                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();
                        if (value >= 0 && value <= 9) {
                            board[i][j] = value;
                            break;
                        } 
                        else 
                        {
                            System.out.println("Enter number between 0 and 9:");
                        }
                    } 
                    else 
                    {
                        System.out.println("Invalid input! Enter an integer:");
                        sc.next(); // clear wrong input
                    }
                }
            }
        }

        if (solve(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }

        sc.close();
    }

    // Solve using backtracking
    public static boolean solve(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isValid(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = 0; // backtrack
                        }
                    }

                    return false; // no number works
                }
            }
        }

        return true; // solved
    }

    // Check if placement is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {

        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num)
                return false;
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num)
                return false;
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    // Print the board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}