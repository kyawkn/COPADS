// file ViewListener.java
// Unit Interface ViewListener


/**
 *
 */
public interface ViewListener {

    /**
     *
     * @param view
     * @param name
     */
    public void join(ModelListener view, String name);

    /**
     *
     * @param view
     * @param row
     * @param col
     */
    public void chosenQueen(ModelListener view, int row, int col);

    /**
     *
     * @param view
     */
    public void newGame(ModelListener view);

    /**
     *
     * @param view
     */
    public void quit(ModelListener view);
}