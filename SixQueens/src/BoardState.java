// File: BoardState.java

import java.util.Arrays;


public class BoardState {

    public static final int rows = 6;
    public static final int cols = 6;



    private Queen[][] queens;
    private int spaceLeft;


    public BoardState() {
        queens = new Queen[rows][cols];
        spaceLeft = 36;
        clear();
    }


    // TODO
    public void clear() {
        for(Queen[] row: queens)
            Arrays.fill(row, Queen.BLANK);

    }

    public void setQueenMark(int row, int col) {
        this.queens[row][col] = Queen.Q;
        this.spaceLeft -= 1;
        // rows & cols
        for (int i = 0; i < row; i++) {
            if (this.queens[row][i] == Queen.BLANK) {
                this.queens[row][i] = Queen.INV;
                this.spaceLeft -= 1;
            }
            if (this.queens[i][col] == Queen.BLANK) {
                this.queens[i][col] = Queen.INV;
                this.spaceLeft -= 1;
            }
        }

        // diagonal
    }
    public Queen getQueenMark(int row, int col) {
        return this.queens[row][col];
    }

    // TODO: check with professor for the algorithm to check the win
    public boolean checkWin () {

        return spaceLeft > 0;

    }

}