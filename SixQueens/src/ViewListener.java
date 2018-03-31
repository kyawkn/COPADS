// file ViewListener.java
// Unit Interface ViewListener


public interface ViewListener {

    public void join(ModelListener view, String name);

    public void chosenQueen(ModelListener view, int row, int col);

    public void newGame(ModelListener view);

    public void quit(ModelListener view);
}