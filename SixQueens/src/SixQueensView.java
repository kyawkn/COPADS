import javax.swing.*;

/**
 * Class SixQueensView provides the user interface for the Six Queens Game.
 *
 * @author  Alan Kaminsky
 * @version 01-Mar-2018
 */
public class SixQueensView implements ModelListener
{

// Hidden data members.

    private static final int GAP = 10;

    private JFrame frame;
    private SixQueensJPanel board;
    private JTextField messageField;
    private JButton newGameButton;

    private ViewListener listener;

// Hidden constructors.

    /**
     * Construct a new Six Queens view object.
     *
     * @param  name  Player's name.
     */
    private SixQueensView
    (String name)
    {
        frame = new JFrame ("Six Queens -- " + name);
        JPanel p1 = new JPanel();
        p1.setLayout (new BoxLayout (p1, BoxLayout.Y_AXIS));
        p1.setBorder (BorderFactory.createEmptyBorder (GAP, GAP, GAP, GAP));
        frame.add (p1);

        board = new SixQueensJPanel();
        board.setFocusable (false);
        board.setAlignmentX (0.5f);
        p1.add (board);
        p1.add (Box.createVerticalStrut (GAP));

        messageField = new JTextField (5);
        messageField.setEditable (false);
        messageField.setFocusable (false);
        messageField.setAlignmentX (0.5f);
        p1.add (messageField);
        p1.add (Box.createVerticalStrut (GAP));

        newGameButton = new JButton ("New Game");
        newGameButton.setFocusable (false);
        newGameButton.setAlignmentX (0.5f);
        p1.add (newGameButton);

        frame.pack();
        frame.setVisible (true);
    }

    public static SixQueensView create
            (final String name)
    {
        final UIRef uiref = new UIRef();
        runOnSwingThread (new Runnable()
        {
            public void run()
            {
                uiref.ui = new SixQueensView (name);
            }
        });
        return uiref.ui;
    }


    public void newGame() {
        runOnSwingThread(new Runnable() {
            @Override
            public void run() {
                board.clear();
            }
        });
    }



    public static class UIRef {
        public SixQueensView ui;
    }


    public void setListener
            (final ViewListener listener)
    {
        runOnSwingThread (new Runnable()
        {
            public void run()
            {
                SixQueensView.this.listener = listener;
            }
        });
    }


    public void setQueenMark(final int row, final int col) {
        runOnSwingThread(new Runnable() {
            public void run() {
                board.setQueen(row, col, true);
            }
        });
    }

    public void yourTurn() {
        runOnSwingThread(new Runnable() {
            @Override
            public void run() {
                messageField.setText("Your turn");
                newGameButton.setEnabled(true);
            }
        });
    }

    public void otherTurn(final String name) {
        runOnSwingThread(new Runnable() {
            public void run() {
                messageField.setText(name + "'s turn");
                newGameButton.setEnabled(true);
            }
        });
    }

    public void youWin() {
        runOnSwingThread(new Runnable() {
            public void run() {
                messageField.setText("You win!");
                newGameButton.setEnabled(true);
            }
        });
    }

    public void otherWin(final String name) {
        runOnSwingThread(new Runnable()
        {
            public void run() {
                messageField.setText(name + "wins!");
                newGameButton.setEnabled(true);
            }
        });
    }


    public void waitingForOther() {
        runOnSwingThread(new Runnable() {
            @Override
            public void run() {
                messageField.setText("Waiting for partner");
                newGameButton.setEnabled(false);
            }
        });
    }

    public void quit() {
        // successful exit
        System.exit(0);

    }

    private static void runOnSwingThread(Runnable task) {
        try {

            SwingUtilities.invokeAndWait (task);

        } catch (Throwable exc) {
            exc.printStackTrace(System.err);
            System.exit(1);
        }
    }


}