public class Board {
    public String[][] board;

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
    }

    /**
     * Check if a given tile has no piece on it
     * @param row
     * @param col
     * @return true iff the tile has no piece
     */
    private boolean isFree(int row, int col) {
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
    private boolean legalMove(int rowStart, int colStart, int rowEnd, int colEnd) {
        // check that the start/endpoints are in-bounds
        if (rowStart <= 0 || rowStart > 8 || colStart <= 0 || colStart > 8
            || rowEnd <= 0 || rowEnd > 8 || colEnd <= 0 || colEnd > 8) {
            return false;
        }
        // check is the target space free and that there is a piece at the start
        if (!isFree(rowEnd, colEnd) || isFree(rowStart, colStart)) {
            return false;
        }

        // determine direction
        if (colStart == colEnd) {
            // move vertically
            if (Math.abs(rowEnd - rowStart) < 2 
                || board[rowStart][colStart].equals(board[rowStart + 1][colStart]) 
                || board[rowStart + 1][colStart].equals(".")) {
                return false;
            }
           
        } else if (rowStart == rowEnd){
            // move horizontally
            if (Math.abs(colEnd - colStart) < 2
                || board[rowStart][colStart].equals(board[rowStart][colStart + 1]) 
                || board[rowStart][colStart + 1].equals(".")) {
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
        System.out.println(rowMoves.length);
        if (rowMoves.length < 2 || colMoves.length < 2 || rowMoves.length != colMoves.length) {
            return false;
        }

        boolean moveVertically = false;
        if(colMoves[0] == colMoves[1]) {
            moveVertically = true;
        }
       
        // check that all moves are legal
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

            if (!legalMove(rowMoves[i], colMoves[i], rowMoves[i + 1], colMoves[i + 1])) {
                return false;
            }
        }

        // TODO: figure out math of the inbetween piece
        // if all of the moves are legal, make the moves and return new board state
        for (int i = 0; i < rowMoves.length - 1; i++) {
            // execute the moves here 
            if (moveVertically) {
                int indxBetween = (rowMoves[i + 1] - rowMoves[i]) / 2;

                board[rowMoves[i + 1]][colMoves[i ]] = board[rowMoves[i]][colMoves[i]];
                board[rowMoves[i] + indxBetween][colMoves[i]] = ".";
                board[rowMoves[i]][colMoves[i]] = ".";    
            } else {
                int indxBetween = (colMoves[i + 1] - colMoves[i]) / 2;

                board[rowMoves[i]][colMoves[i + 1]] = board[rowMoves[i]][colMoves[i]];
                board[rowMoves[i]][colMoves[i]] = ".";
                board[rowMoves[i] ][colMoves[i] + indxBetween] = ".";    
            }
        }
        return true;
    }

    /**
     * Given a board, print out its contents
     * @param board to print
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.board[i][j] + " ");
                if (j == 8) {
                    System.out.println();
                }
            }
        }
        System.out.println(); // extra new line for it to look nice (imo)
    }
}
