import java.util.ArrayList;
import java.util.Scanner;
/*
 * TODO: 
 *      "player moving: computer / player" to start, and naturally does opposite from there
 *          * how keep track / determine move order?
 *      repeat player / computer turns win condition is had
 *          * if at start of player's turn, all valid moves = 0, declare the other player the victor.
 * Computer turn:
 *      * move input & check that is valid
 *          * first, find all valid moves & randomly pick a move to do. 
 *      * min/max the value is how many viable move option the player has vs how many viable move options the computer has
 */

public class Konane {

    /**
     * Gets the human player's inputted moves.
     * @return player's inputted moves in the form of a 2D integer array
     */
    public int[][] getPlayerMove() {
        ArrayList<Integer> rowMoves = new ArrayList<>();
        ArrayList<Integer> colMoves = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Format moves as [x-coordinate] [y-coordinate]");
            System.out.print("Move: ");
            String input = scanner.nextLine();

            while (true) {
                if ((input.toLowerCase()).equals("e")) {
                    break;
                } else {
                    String[] line = input.split(" ");

                    if (line.length == 1 || line.length > 2) {
                        System.out.println("Invalid input!");
                    } else {
                        int rowToAdd, colToAdd;

                        try {
                            rowToAdd = Integer.parseInt(line[0]);
                            colToAdd = Integer.parseInt(line[1]);

                            rowMoves.add(rowToAdd); 
                            colMoves.add(colToAdd);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input!");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid input!");
                        }
                    }
                    
                    System.out.print("Move (E to finish input): ");
                    input = scanner.nextLine();
                }
            }

            // conversion
            int[] rowArr = new int[rowMoves.size()];
            int[] colArr = new int[colMoves.size()];

            for (int i = 0; i < rowArr.length; i++) {
                System.out.print(rowMoves.get(i));
                System.out.println(" " + colMoves.get(i));
                rowArr[i] = rowMoves.get(i);
                colArr[i] = colMoves.get(i);
            }

            int[][] ret = {rowArr, colArr};
            return ret;
        }
    }

    public static void main(String args[]) {
        Konane game = new Konane();
        Board board = new Board();
        
        // getting player moves code (test)
        // int[][] playerMoves = game.getPlayerMove();
        // int[] row = playerMoves[0];
        // int[] col = playerMoves[1];

        // for(int j = 0; j < row.length; j++) {
        //     System.out.print("<" + row[j] + " " + col[j] + "> ");
        //     System.out.print(" ");            
        // }
        // System.out.println();
        
        System.out.println("Who moves first? Player or computer? ");

        try (Scanner scanner = new Scanner(System.in)) {
            String currPlayer = "";
            String side = ""; // TODO: how to alternate sides
            String playerSide = "";
            String computerSide = "";


            String firstTurn = scanner.nextLine();
            if((firstTurn.toLowerCase()).equals("player")) {
                currPlayer = "player";
                playerSide = "X";
                side = playerSide;
                computerSide = "O";
            } else if((firstTurn.toLowerCase()).equals("computer")) {
                currPlayer = "computer";
                playerSide = "O";
                side = computerSide;
                computerSide = "X";
            } else {
                System.out.println("Invalid input.");
            }

            // first turn stuff here
            while(board.gameState(side)) {
                if (currPlayer.equals("player")) {
                    
                    
                    // TODO: get player moves, check if they're legal, if not keep looping until they are legal
                    
                    side = computerSide;
                } else {
                    // computer turn
                    board.makeMove(computerSide);
                    side = playerSide;
                }
            }
            /*
             * starting move: <8, 8>, <1, 1>, <5, 5>, <4,4>
             * make function to read input and convert to coordinates
             * how to get input? [xcoor ycoor] [xcoor ycoor] [input by enter]
             * print out as "moves <x, y> to <x, y> to <x, y>" etc ad nauseum
             */
        }
    }
}