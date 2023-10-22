import java.util.ArrayList;

public class Board {
    public String[][] board;
    private int legalMoves;

    public Board(){
        // create and innit a new board
        board = new String[9][9];

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

        legalMoves = 0;
    }

    public Board(String[][] board) {
        this.board = board;
    }

    /**
     * Check if a given tile has no piece on it
     * @param row
     * @param col
     * @return true iff the tile has no piece
     */
    private boolean isFree(int row, int col, String[][] boardState) {
        if(board[row][col].equals(".")) {
            return true;
        }
        return false;
    }

    /**
     * Given a set of moves a player would like to make, check to see if the moves are 
     * legal in the given board state
     * @param board - the current state of the board
     * @return true iff the move is legal
     */
    private boolean legalMove(int rowStart, int colStart, int rowEnd, int colEnd, String[][] boardState) {
        // System.out.println("(" + rowStart + "," + colStart + ") to " + "(" + rowEnd + "," + colEnd + ")");
        // printBoard();
        // check that the start/endpoints are in-bounds
        if (rowStart <= 0 || rowStart > 8 || colStart <= 0 || colStart > 8
            || rowEnd <= 0 || rowEnd > 8 || colEnd <= 0 || colEnd > 8) {
            return false;
        }
        // check is the target space free and that there is a piece at the start
        if (!isFree(rowEnd, colEnd, boardState) || isFree(rowStart, colStart, boardState)) {
            return false;
        }

        // determine direction
        if (colStart == colEnd) {
            int indxBetween = (rowEnd - rowStart) / 2;
            // System.out.println(rowStart + indxBetween);
            // move vertically
            if (Math.abs(rowEnd - rowStart) < 2 
                || board[rowStart][colStart].equals(board[rowStart + indxBetween][colStart]) 
                || board[rowStart + indxBetween][colStart].equals(".")) {
                return false;
            }
           
        } else if (rowStart == rowEnd){
            int indxBetween = (colEnd - colStart) / 2;
            // move horizontally
            if (Math.abs(colEnd - colStart) < 2
                || board[rowStart][colStart].equals(board[rowStart][colStart + indxBetween]) 
                || board[rowStart][colStart + indxBetween].equals(".")) {
                return false;
            }
        } 

        return true;
    }

    /**
     * Executes the given move(s) iff they're legal
     * @param rowMoves
     * @param colMoves
     * @return true iff the given sets of moves is executable
     */
    public boolean movePiece(int[] rowMoves, int[] colMoves) {
        // catch cases where the move inputs aren't right
        if (rowMoves.length < 2 || colMoves.length < 2 || rowMoves.length != colMoves.length) {
            return false;
        }

        boolean moveVertically = false;
        if(colMoves[0] == colMoves[1]) {
            moveVertically = true;
        }
        
        // check that all moves are legal
        String[][] testBoard = this.board;

        for (int i = 0; i < rowMoves.length - 1; i++) {
            // verify that the moves are in the same direction 
            if (moveVertically) {
                if (rowMoves[i] == rowMoves[i + 1]) {
                    return false; 
                } else if(i > 1 && i < rowMoves.length - 1) {
                    if (!(rowMoves[i - 1] < rowMoves[i] && rowMoves[i] < rowMoves[i + 1])
                    && !(rowMoves[i - 1] > rowMoves[i] && rowMoves[i] > rowMoves[i + 1])) {
                        return false;
                    }
                }
            } else if (!moveVertically) {
                if (colMoves[i] == colMoves[i + 1]) { 
                    return false; 
                } else if (i > 1 && i < rowMoves.length - 1) {
                    if (!(colMoves[i - 1] < colMoves[i] && colMoves[i] < colMoves[i + 1])
                    && !(colMoves[i - 1] > colMoves[i] && colMoves[i] > colMoves[i + 1])) {
                        return false;
                    }
                }
            }

            if (!legalMove(rowMoves[i], colMoves[i], rowMoves[i + 1], colMoves[i + 1], testBoard)) {
                return false;
            } else {
                // execute the moves here 
                if (moveVertically) {
                    int indxBetween = (rowMoves[i + 1] - rowMoves[i]) / 2;

                    board[rowMoves[i + 1]][colMoves[i]] = board[rowMoves[i]][colMoves[i]];
                    board[rowMoves[i] + indxBetween][colMoves[i]] = ".";
                    board[rowMoves[i]][colMoves[i]] = ".";    
                } else {
                    int indxBetween = (colMoves[i + 1] - colMoves[i]) / 2;

                    board[rowMoves[i]][colMoves[i + 1]] = board[rowMoves[i]][colMoves[i]];
                    board[rowMoves[i]][colMoves[i]] = ".";
                    board[rowMoves[i]][colMoves[i] + indxBetween] = ".";    
                }
            }
        }

        // if all of the moves are legal, make the moves and return new board state
        this.board = testBoard;
        return true;
    }

    /**
     * Finds all the possible (legal) moves on the board for a given side and then finds them
     * probably make it private later but. make some kind of list (ArrayList?) of all the legal moves, and then pick one from it
     * @param side - the side (X or O) for whom all legal moves should be found for
     */
    public ArrayList<int[][]> allLegalMoves(String side) {
        String[][] saveState = copy(board);
        // Board testBoard = new Board(board); 
        // ArrayList<MoveSequence> moveSeq = new ArrayList<>();

        ArrayList<int[][]> allMoves = new ArrayList<int[][]>();

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (!board[i][j].equals(side)) {
                    continue;
                }
                // System.out.println("(" + i + ", " + j + ")");
                ArrayList<Integer> rowMoves, colMoves;
                
                // check all vertical moves
                for (int k = -2; k <= 2; k += 4) {
                    board = copy(saveState);
                    // MoveSequence currMoves = new MoveSequence();

                    rowMoves = new ArrayList<>();
                    colMoves = new ArrayList<>();
                    rowMoves.add(i);
                    colMoves.add(j);
                    
                    int index = k;


                    // add second point
                    rowMoves.add(i);
                    colMoves.add(j + index);
                    // if (k < 0) {
                    //     colMoves.add(j - 2);
                    //     // currMoves.add(i, j - 2);
                    // } else {
                    //     colMoves.add(j + 2);
                    //     // currMoves.add(i, j + 2);
                    // }
                    
                    while (true) {
                        int[] arrRowMoves = toArray(rowMoves);
                        int[] arrColMoves = toArray(colMoves);
                        // int[] arrRowMoves = currMoves.getRowMoves();
                        // int[] arrColMoves = currMoves.getColMoves();
                       
                        if (movePiece(arrRowMoves, arrColMoves)) {
                            // moveSeq.add(currMoves); 
                            index += k;
                            // currMoves.add(i, j + index);
                            int[][] toAdd ={arrRowMoves, arrColMoves};
                            allMoves.add(toAdd);
                            rowMoves.add(i);
                            colMoves.add(j + index);
                            
                            board = copy(saveState); // have to reset the board every time...
                            // printBoard();
                        } else {
                            // printArray(arrRowMoves);
                            // printArray(arrColMoves);
                            break;
                        }
                    }
                }

                // check all horizontal moves
                for (int k = -2; k <= 2; k += 4) {
                    board = copy(saveState);
                    rowMoves = new ArrayList<>();
                    colMoves = new ArrayList<>();
                    rowMoves.add(i);
                    colMoves.add(j);
                    MoveSequence currMoves = new MoveSequence();
                    currMoves.add(i, j);
                    int index = k;

                    // add second point
                    rowMoves.add(i + index);
                    colMoves.add(j);
                    // if (k < 0) {
                    //     rowMoves.add(i - 2);
                    //     // currMoves.add(i - 2, j);
                    // } else {
                    //     rowMoves.add(i + 2);
                    //     // currMoves.add(i + 2, j);
                    // }

                    while (true) {
                        int[] arrRowMoves = toArray(rowMoves);
                        int[] arrColMoves = toArray(colMoves);
                        // int[] arrRowMoves = currMoves.getRowMoves();
                        // int[] arrColMoves = currMoves.getColMoves();

                        if (movePiece(arrRowMoves, arrColMoves)) {
                            // moveSeq.add(currMoves);
                            index += k;
                            // currMoves.add(i + index, j);

                            int[][] toAdd ={arrRowMoves, arrColMoves};

                            allMoves.add(toAdd);
                            rowMoves.add(i + index);
                            colMoves.add(j);
                            
                            // System.out.println();
                            board = copy(saveState);
                            
                        } else {
                            break;
                        }
                    }
                }
            } 
        }
       
        // System.out.println("Test board");
        // testBoard.printBoard();

        // System.out.println("Save State");
        // for (int i = 0; i < 9; i++) {

        //     if (i == 1) System.out.println();

        //     for (int j = 0; j < 9; j++) {
        //         System.out.print(saveState[i][j] + " ");

        //         if (j == 0) {System.out.print(" ");}
        //         if (j == 8) {System.out.println();}
        //     }
        // }
        // // System.out.println();
        // printBoard();
        board = copy(saveState);
        // printBoard();
        return allMoves;
        // return allMoves;
    }

    /**
     * Give an ArrayList, convert it to an array
     * @param lst - the ArrayList to convert
     * @return ret - the ArrayList converted to an array
     */
    private int[] toArray(ArrayList<Integer> lst) {
        int[] retArr = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            retArr[i] = lst.get(i);
        }
        return retArr; 
    }

    // private void printArray(int[] moves) {
    //     for (int i = 0; i < moves.length; i++) {
    //         System.out.print(moves[i]);
    //     }
    //     System.out.println();
    // }

    /**
     * Takes a given board and copies it to a new object 
     * (to get around bugs caused by pass by value)
     * @param board - board to copy
     * @return 
     */
    private String[][] copy(String[][] board) {
        String[][] retBoard = new String[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                retBoard[i][j] = board[i][j];
            }
        }

        return retBoard;
    }

    /**
     * Given a board, print out its contents
     * @param board to print
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {

            if (i == 1) System.out.println();

            for (int j = 0; j < 9; j++) {
                System.out.print(this.board[i][j] + " ");

                if (j == 0) {System.out.print(" ");}
                if (j == 8) {System.out.println();}
            }
        }
        System.out.println();
    }
}
