package MayinTarlasi;
import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    private int rows;
    private int cols;
    private int[][] board;
    private boolean[][] mines;

    public MineSweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new int[rows][cols];
        mines = new boolean[rows][cols];
        initializeBoard();
        placeMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = -1; // Unrevealed cell
                mines[i][j] = false;
            }
        }
    }

    private void placeMines() {
        int totalMines = rows * cols / 4;
        Random rand = new Random();

        for (int i = 0; i < totalMines; i++) {
            int row, col;
            do {
                row = rand.nextInt(rows);
                col = rand.nextInt(cols);
            } while (mines[row][col]);

            mines[row][col] = true;
        }
    }

    boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (isValid(newRow, newCol) && mines[newRow][newCol]) {
                count++;
            }
        }

        return count;
    }

    public void revealCell(int row, int col) {
        if (!isValid(row, col) || board[row][col] != -1) {
            return;
        }

        if (mines[row][col]) {
            System.out.println("Maayına Bastın Oyun Bitti");
            printBoard();
            System.exit(0);
        }

        int count = countAdjacentMines(row, col);
        board[row][col] = count;

        if (count == 0) {
            int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

            for (int i = 0; i < 8; i++) {
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                revealCell(newRow, newCol);
            }
        }
    }

    public boolean isGameWon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1 && !mines[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("Güncel Oyun Tahtası:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    System.out.print("- ");
                } else if (mines[i][j]) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}