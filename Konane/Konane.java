import java.util.ArrayList;
import java.util.Scanner;

public class Konane {
    public static void main(String args[]) {
        Board board = new Board();
        board.board[4][4] = ".";
        board.board[4][5] = ".";

        System.out.println("Who moves first? Player or computer? ");
        String currPlayer = "";

        try (Scanner scanner = new Scanner(System.in)) {
            String side = "";
            String playerSide = "";
            String computerSide = "";

            String firstTurn = scanner.nextLine();
            if((firstTurn.toLowerCase()).equals("player")) {
                currPlayer = "player";
                playerSide = "X";
                computerSide = "O";
                side = playerSide;
            } else if((firstTurn.toLowerCase()).equals("computer")) {
                currPlayer = "computer";
                playerSide = "O";
                computerSide = "X";
                side = computerSide;
            } else {
                System.out.println("Invalid input.");
            }

            board.printBoard();
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

                        System.out.println("Your side: " + playerSide + "\nFormat moves as: x-coordinate y-coordinate");
                        System.out.print("Start Position: ");  

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
                        
                                System.out.print("Moves To (E to finish giving inputs): ");
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
                    currPlayer = "computer";
                } else {
                    // computer turn
                    System.out.println("Computer side: " + computerSide);
                    board.makeMove(computerSide);
                    side = playerSide;
                    currPlayer = "player";
                }

                board.printBoard();
            }
        }
        
        board.printBoard();
        String winner = currPlayer.equals("player") ? "Computer" : "Player";
        System.out.println(winner + " wins!");
    }
}