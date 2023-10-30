public class Move {
    private int value; 
    private int[][] moves;

    public Move() {

    }

    public Move(int value, int[][] moves) {
        this.value = value;
        this.moves = moves; 
    }

    public int getVal() {
        return value;
    }

    public int[][] getMoves() {
        return moves;
    }

    public int[] rowMoves() {
        return moves[0];
    }

    public int[] colMoves() {
        return moves[1];
    }
}
