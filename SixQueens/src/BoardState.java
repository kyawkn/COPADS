// File: BoardState.java
// Unit: Class BoardState

// ******************************************************************************
// This file uses BoardState.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// ******************************************************************************
import java.util.Arrays;

/**
 * Class BoardState provides the state of the SixQueens Game
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */
public class BoardState {

    // data members
    private static final int rows = 6;
    private static final int cols = 6;

    private Queen[][] queens;
    private int spaceLeft;

    // constructors

    /**
     * Construct a new Board
     */
    public BoardState() {
        queens = new Queen[rows][cols];
        clear();
    }


    /**
     * Clear the board and set square as BLANK
     * and total space of 36
     */
    public void clear() {
        for(Queen[] row: queens)
            Arrays.fill(row, Queen.BLANK);
        spaceLeft = 36;

    }

    /**
     * Set the QUEEN mark at the location, and all the possible invisible
     * squares
     * @param row row position to place the queen mark
     * @param col col position to place the queen mark
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
     * return the mark at the location
     * @param row row index
     * @param col col index
     * @return Queen mark at row, col
     */
    public Queen getQueenMark(int row, int col) {
        return this.queens[row][col];
    }


    /**
     * check the total spaces left and if there is not place left
     * then current player wins the game
     * @return Boolean referring if the current player has won the game or not
     */
    public boolean checkWin () {

        return this.spaceLeft <= 0 ;

    }

}