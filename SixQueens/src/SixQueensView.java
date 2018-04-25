// File: SixQueensView.java
// Unit: Class SixQueensView

// *********************************************************************************
// This file uses TicTacToeView.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// **********************************************************************************

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class SixQueensView provides the user interface for the Six Queens Game.
 *
 * @author  Alan Kaminsky
 * @author Kyaw Khant Nyar
 * @version 03-April-2018
 */
public class SixQueensView implements ModelListener
{

// Hidden data members.

    private static final int GAP = 10;

    private static final int size = 6;

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

        board.setListener(new SixQueensJPanelListener() {
            @Override
            public void squareClicked(int r, int c) {
                if (listener!=null)
                    listener.chosenQueen(SixQueensView.this, r, c);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener!=null)
                    listener.newGame(SixQueensView.this);
            }

        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (listener!=null)
                    listener.quit(SixQueensView.this);
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible (true);
    }

    /**
     * Construct a new SixQueens view object
     * @param name player's name
     * @return view object
     */
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


    /**
     * Report that a new game was started.
     */
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


    /**
     * Set the view listener
     * @param listener
     */
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


    /**
     * Report that a Queen was placed on a square
     * @param row row position
     * @param col col position
     */
    public void setQueenMark(final int row, final int col) {
        runOnSwingThread(new Runnable() {
            public void run() {

                board.setQueen(row, col, true);

                for (int i = 0; i < size; i++) {
                    if (i != row)
                        board.setVisible(i, col, false);
                    if(i != col)
                        board.setVisible(row, i, false);
                }


                // north west diagonal row - col -


                int n = row - 1;
                int w = col - 1;
                while (n >= 0 && w >= 0) {
                    board.setVisible(n, w, false);
                    n -= 1;
                    w -= 1;
                }


                // south west diagonal row + col -
                int s = row + 1;
                w = col - 1;
                while (s < size && w >= 0) {
                    board.setVisible(s, w, false);
                    s += 1;
                    w -= 1;
                }

                // north east diagonal row - col +
                n = row - 1;
                int e = col + 1;
                while (n >= 0 && e < size) {
                    board.setVisible(n, e, false);
                    n -= 1;
                    e += 1;
                }

                // south east diagonal row + col +
                s = row + 1;
                e = col + 1;
                while (s < size && e < size) {
                    board.setVisible(s, e, false);
                    s += 1;
                    e += 1;
                }

            }
        });
    }

    /**
     * Report that it's the player's turn
     */
    public void yourTurn() {
        runOnSwingThread(new Runnable() {
            @Override
            public void run() {
                messageField.setText("Your turn");
                newGameButton.setEnabled(true);
            }
        });
    }

    /**
     * Report that it's the other player's turn
     * @param name other player's name
     */
    public void otherTurn(final String name) {
        runOnSwingThread(new Runnable() {
            public void run() {
                messageField.setText(name + "'s turn");
                newGameButton.setEnabled(true);
            }
        });
    }

    /**
     * Report that the player wins
     */
    public void youWin() {
        runOnSwingThread(new Runnable() {
            public void run() {
                messageField.setText("You win!");
                newGameButton.setEnabled(true);
            }
        });
    }

    /**
     * Report that the other player wins
     * @param name the other player's name
     */
    public void otherWin(final String name) {
        runOnSwingThread(new Runnable()
        {
            public void run() {
                messageField.setText(name + " wins!");
                newGameButton.setEnabled(true);
            }
        });
    }


    /**
     * Report that the player is waiting for a partner
     */
    public void waitingForOther() {
        runOnSwingThread(new Runnable() {
            @Override
            public void run() {
                messageField.setText("Waiting for partner");
                newGameButton.setEnabled(false);
            }
        });
    }

    /**
     * Report that a player has quit
     */
    public void quit() {
        // successful exit
        System.exit(0);

    }

    // Hidden operations
    /**
     * Execute the given runnable object on the swing thread
     * @param task
     */
    private static void runOnSwingThread(Runnable task) {
        try {
            SwingUtilities.invokeAndWait (task);
        } catch (Throwable exc) {
            exc.printStackTrace(System.err);
            System.exit(1);
        }
    }


}