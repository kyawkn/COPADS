import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class SixQueensView provides the user interface for the Six Queens Game.
 *
 * @author  Alan Kaminsky
 * @version 01-Mar-2018
 */
public class SixQueensView
{

// Hidden data members.

    private static final int GAP = 10;

    private JFrame frame;
    private SixQueensJPanel board;
    private JTextField messageField;
    private JButton newGameButton;

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

}