import java.util.ArrayList;

public class Konane {

    public static void main(String args[]) {

        Board board = new Board();
        
        board.printBoard();

        board.board[4][4] = ".";
        board.board[2][4] = ".";
        board.printBoard();
        int[] rowMoves = {6, 4, 2};
        int[] colMoves = {4, 4, 4};


        if(board.movePiece(rowMoves, colMoves)) {
            board.printBoard();
        } else {
            System.out.println("Illegal Move!");
        }

        ArrayList<int[][]> moves = board.allLegalMoves("O");
        System.out.print(moves.size());
        /*
         * TODO: 
         *      decide who moves first (player or computer) - function that is 
         *      "player moving: computer / player" to start, and naturally does opposite from there
         *      until win condition is had
         *      move input & check that is valid
         *      min/max the value is how many viable move option the player has vs how many viable move options the computer has
         */
    }
}