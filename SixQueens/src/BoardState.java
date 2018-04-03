// File: BoardState.java

import java.util.Arrays;


public class BoardState {

    public static final int rows = 6;
    public static final int cols = 6;



    private Queen[][] queens;
    private int spaceLeft;


    public BoardState() {
        queens = new Queen[rows][cols];
        clear();
    }


    /**
     *
     */
    public void clear() {
        for(Queen[] row: queens)
            Arrays.fill(row, Queen.BLANK);
        spaceLeft = 36;

    }

    /**
     *
     * @param row
     * @param col
     */
    public void setQueenMark(int row, int col) {
        this.queens[row][col] = Queen.Q;
        this.spaceLeft -= 1;
        // rows & cols

        for (int i = 0; i < rows; i++) {
            if (this.queens[row][i] == Queen.BLANK) {
                this.queens[row][i] = Queen.INV;
                this.spaceLeft -= 1;
            }
            if (this.queens[i][col] == Queen.BLANK) {
                this.queens[i][col] = Queen.INV;
                this.spaceLeft -= 1;
            }
        }

        // north west diagonal row - col -
        int n = row -1;
        int w = col -1;
        while (n >= 0 && w >= 0) {
            if (this.queens[n][w] != Queen.INV) {
                this.queens[n][w] = Queen.INV;
                this.spaceLeft -= 1;
            }
            n -= 1;
            w -= 1;
        }


        // south west diagonal row + col -
        int s = row + 1;
        w = col - 1;
        while (s < rows && w >= 0) {
            if (this.queens[s][w] != Queen.INV) {
                this.queens[s][w] = Queen.INV;
                this.spaceLeft -= 1;
            }
            s += 1;
            w -= 1;
        }

        // north east diagonal row - col +
        n = row - 1;
        int e = col + 1;
        while (n >= 0 && e < cols) {
            if (this.queens[n][e] != Queen.INV) {
                this.queens[n][e] = Queen.INV;
                this.spaceLeft -= 1;
            }
            n -= 1;
            e += 1;
        }

        // south east diagonal row + col +
        s = row + 1;
        e = col + 1;
        while (s < rows && e < cols) {
            if (this.queens[s][e] != Queen.INV) {
                this.queens[s][e] = Queen.INV;
                this.spaceLeft -= 1;
            }
            s += 1;
            e += 1;
        }



    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public Queen getQueenMark(int row, int col) {
        return this.queens[row][col];
    }


    /**
     *
     * @return
     */
    public boolean checkWin () {

        return this.spaceLeft <= 0 ;

    }

}