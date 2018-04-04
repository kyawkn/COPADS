// file ViewListener.java
// Unit Interface ViewListener

/**
 * Interface ViewListener specifies the interface for an object that receives
 * reports from the View in the SixQueens game.
 */
public interface ViewListener {

    /**
     * Report that a player joined the game
     * @param view View object making the report
     * @param name Player's name
     */
    public void join(ModelListener view, String name);

    /**
     * Report that a queen is placed in a square
     * @param view view object making the report
     * @param row queen square row pos
     * @param col queen square col pos
     */
    public void chosenQueen(ModelListener view, int row, int col);

    /**
     * report that a new game was requested
     * @param view view object making the report
     */
    public void newGame(ModelListener view);

    /**
     * report that the player quit.
     * @param view view object making the report
     */
    public void quit(ModelListener view);
}