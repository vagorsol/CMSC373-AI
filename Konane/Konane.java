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
        ArrayList<int[][]> moves = board.allLegalMoves("X");

        // if(board.movePiece(rowMoves, colMoves)) {
        //     board.printBoard();
        // } else {
        //     System.out.println("Illegal Move!");
        // }

        // ArrayList<int[][]> moves = board.allLegalMoves("X"); // this seems to update the board..
        System.out.println(moves.size());
        for (int i = 0; i < moves.size(); i++) {
            int[][] temp = moves.get(i);
            for(int j = 0; j < temp.length; j++) {
                for(int k = 0; k < temp[j].length; k++) {
                    System.out.print(temp[j][k]); // will have to index by k/v - maybe should be hash but int[]?? can figure this later kinda done for today tbh
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        // should have (6, 4)


        // board.printBoard();
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