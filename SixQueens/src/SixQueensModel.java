
//
//


public class SixQueensModel implements ViewListener {


    private String firstPlayer;
    private String secondPlayer;
    private ModelListener firstView;
    private ModelListener secondView;
    private ModelListener turn;
    private boolean isGameOver;
    private BoardState gameBoard = new BoardState();


    /**
     * constructor
     */
    public SixQueensModel() {
    }


    /**
     *
     * @param view
     * @param name
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
     *
     * @param view
     * @param row
     * @param col
     */
    public synchronized void chosenQueen(ModelListener view, int row, int col) {

        // TODO change the mark values
        if (view != turn || gameBoard.getQueenMark(row, col) != Queen.BLANK)
            return;
        else if (view == firstView)
            setQueen(firstView, row, col);
        else
            setQueen(secondView, row, col);

    }

    /**
     *
     * @param view
     */
    public void newGame(ModelListener view) {

        if(secondPlayer != null)
            createNewGame();

    }

    /**
     *
     * @param view
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
     *
     * @return
     */
    public synchronized boolean isGameOver() {
        return isGameOver;
    }

    /**
     *
     */
    private void createNewGame() {

        gameBoard.clear();
        firstView.newGame();
        secondView.newGame();

        turn = firstView;
        firstView.yourTurn();
        secondView.otherTurn(firstPlayer);
    }


    /**
     *
     * @param current
     * @param row
     * @param col
     */
    private void setQueen(ModelListener current, int row, int col) {

        gameBoard.setQueenMark(row, col);
        firstView.setQueenMark(row, col);
        secondView.setQueenMark(row, col);

        boolean win = gameBoard.checkWin();

        if (win) {

            turn = null;

            if (current == firstView) {
                firstView.youWin();
                secondView.otherWin(firstPlayer);
            } else {
                secondView.youWin();
                firstView.otherWin(secondPlayer);
            }
        } else {
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