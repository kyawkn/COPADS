// file Mark.java
// Unit Enum Queen

// *********************************************************************************
// This file uses Mark.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// **********************************************************************************
/**
 * Enum Queen enumerates the marks that can appear on the SixQueens game board.
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */

public enum Queen {

    /**
     * Visible empty blank square
     */
    BLANK,
    /**
     * Queen's Square
     */
    Q,

    /**
     * Invisible Space Square
     */
    INV;


    /**
     * valueOf converts the given ordinal into an enum.
     *
     * @param ordinal Ordinal
     * @return Enum Queen
     * @exception IllegalArgumentException is thrown if ordinal is invalid.
     */
    public static Queen valueOf (int ordinal) {
        switch (ordinal) {
            case 0: return BLANK;
            case 1: return Q;
            case 2: return INV;
            default:
                throw new IllegalArgumentException (String.format
                        ("Queen.valueOf(): illegal ordinal value = %d ", ordinal));
        }
    }

}