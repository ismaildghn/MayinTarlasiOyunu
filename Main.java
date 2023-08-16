package MayinTarlasi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Satır Sayısını Girin: ");
        int rows = scanner.nextInt();
        System.out.print("Sütun Sayısını Girin: ");
        int cols = scanner.nextInt();

        MineSweeper game = new MineSweeper(rows, cols);
        game.printBoard();

        while (!game.isGameWon()) {
            System.out.print("Satır Girin: ");
            int row = scanner.nextInt();
            System.out.print("Sütun Girin: ");
            int col = scanner.nextInt();

            if (!game.isValid(row, col)) {
                System.out.println("Geçersiz Giriş Tekrar Deneyin.");
                continue;
            }

            game.revealCell(row, col);
            game.printBoard();
        }

        System.out.println("Tebrikler Oyunu Kazandın!");
    }
}
