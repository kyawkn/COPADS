// File: SixQueensModel.java
// Unit: SixQueensModel

// *********************************************************************************
// This file uses TicTacToeModel.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// **********************************************************************************
/**
 * Class SixQueensModel provides the application logic for the SixQueens game
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */
public class SixQueensModel implements ViewListener {

    // private data members
    private String firstPlayer;
    private String secondPlayer;
    private ModelListener firstView;
    private ModelListener secondView;
    private ModelListener turn;
    private boolean isGameOver;
    private BoardState gameBoard = new BoardState();


    // Exported constructors
    /**
     * Construct a new SixQueens model object.
     */
    public SixQueensModel() {
    }

    // public operations

    /**
     * Report that a player joined the game
     * @param view View object
     * @param name player's name
     */
    public synchronized void join(ModelListener view, String name) {

        if(firstPlayer == null) {
            firstPlayer = name;
            firstView = view;
            firstView.waitingForOther();
        } else {
            secondPlayer = name;
            secondView = view;
            createNewGame();
        }
    }

    /**
     * Report the Queen placed
     * @param view View object making the report
     * @param row row index
     * @param col col index
     */
    public synchronized void chosenQueen(ModelListener view, int row, int col) {

        if (view != turn || gameBoard.getQueenMark(row, col) != Queen.BLANK)
            return;
        else if (view == firstView)
            setQueen(firstView, row, col);
        else
            setQueen(secondView, row, col);

    }

    /**
     * Report that a new game was requested
     * @param view View object reporting
     */
    public void newGame(ModelListener view) {

        if(secondPlayer != null)
            createNewGame();

    }

    /**
     * Report that the player quits
     * @param view View object reporting
     */
    public void quit(ModelListener view) {
        if (firstView != null)
            firstView.quit();
        if (secondView != null)
            secondView.quit();
        turn = null;
        isGameOver = true;
    }


    /**
     * Check whether the game session is over
     * @return True if game is over, false if not
     */
    public synchronized boolean isGameOver() {
        return isGameOver;
    }

    // hidden operations

    /**
     * Start a new game
     */
    private void createNewGame() {
        // Clear the board and inform the players
        gameBoard.clear();
        firstView.newGame();
        secondView.newGame();

        // player one takes the first turn
        turn = firstView;
        firstView.yourTurn();
        secondView.otherTurn(firstPlayer);
    }


    /**
     * Set a mark on the board and switch turns
     * @param current view object of the current player
     * @param row Queen row position
     * @param col Queen col position
     */
    private void setQueen(ModelListener current, int row, int col) {

        // Set the mark and inform the players
        gameBoard.setQueenMark(row, col);
        firstView.setQueenMark(row, col);
        secondView.setQueenMark(row, col);

        // check winning state
        boolean win = gameBoard.checkWin();

        // Update game state and inform the players
        if (win) {

            // current player
            turn = null;

            if (current == firstView) {
                firstView.youWin();
                secondView.otherWin(firstPlayer);
            } else {
                secondView.youWin();
                firstView.otherWin(secondPlayer);
            }
        } else {
            // no win or draw yet
            if (current == firstView) {
                turn = secondView;
                firstView.otherTurn(secondPlayer);
                secondView.yourTurn();
            } else {
                turn = firstView;
                firstView.yourTurn();
                secondView.otherTurn(firstPlayer);
            }
        }
    }
}