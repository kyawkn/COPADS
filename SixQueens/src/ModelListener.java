// File: ModelListener.java
// Unit: Interface ModelListener

// ******************************************************************************
// This file uses ModelListener.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// ******************************************************************************

/**
 * Interface ModelListener specifies the interface for an object that
 * receives reports from the Model in the SixQueens game.
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */
public interface ModelListener {

    // public operations

    /**
     * Report that a new game was started.
     */
    public void newGame();

    /**
     * Report that a queen was placed on a square.
     * @param row row position
     * @param col col position
     */
    public void setQueenMark(int row, int col);

    /**
     * Report that the player is waiting for a partner
     */
    public void waitingForOther();

    /**
     * Report that it's the player's turn
     */
    public void yourTurn();

    /**
     * Report that it's the other player's turn
     * @param name other player's name
     */
    public void otherTurn(String name);

    /**
     * Report that the player has won
     */
    public void youWin();

    /**
     * Report that the other player has won
     * @param name the other player's name
     */
    public void otherWin(String name);

    /**
     * Report that a player quits.
     */
    public void quit();

}