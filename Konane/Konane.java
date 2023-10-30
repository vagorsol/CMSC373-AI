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
    public static void main(String args[]) {
        Board board = new Board();

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
                    int[] row, col;
                    int repeat = 0;

                    // getting player moves
                    do {
                        if (repeat > 0) {
                            System.out.println("Illegal Moves! Please input legal moves");
                        }
                        ArrayList<Integer> rowMoves = new ArrayList<>();
                        ArrayList<Integer> colMoves = new ArrayList<>();

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
                            rowArr[i] = rowMoves.get(i);
                            colArr[i] = colMoves.get(i);
                        }

                        int[][] playerMoves = {rowArr, colArr};
                        row = playerMoves[0];
                        col = playerMoves[1];
                        repeat++;
                    } while (!board.movePiece(row, col));

                    // output player move
                    System.out.print("moves ");
                    for(int j = 0; j < row.length; j++) {
                        System.out.print("<" + row[j] + " " + col[j] + "> ");
                            if (j < row.length - 1) {
                            System.out.print("to ");
                        }       
                    }
                    System.out.println();
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