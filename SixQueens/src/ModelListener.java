
public interface ModelListener {

    public void newGame();

    public void setQueenMark(int row, int col);

    public void waitingForOther();

    public void yourTurn();

    public void otherTurn(String name);

    public void youWin();

    public void otherWin(String name);

    public void quit();

}