public class Konane {

    /*
     * Given a board, print out its contents
     * @param board to print
     */
    public static void printBoard(String[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                if (j == 8) {
                    System.out.println();
                }
            }
        }
    }
    public static void main(String args[]) {
        // create a 9 x 9 array (board) and initialize it
        String[][] board = new String[9][9];
        board[0][0] = " ";
        for (int i = 1; i < 9; i++) {
            board[0][i] = Integer.toString(i);
            board[i][0] = Integer.toString(i);
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if ((j % 2 == 0 && i % 2 !=0) || (i % 2 == 0 && j % 2 != 0)) {
                    board[i][j] = "O";
                } else {
                    board[i][j] = "X";
                }
            }
        }

        printBoard(board);
    }
}