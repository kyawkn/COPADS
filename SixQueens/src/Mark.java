// file Mark.java
//
/**
 * Enum Mark enumrates the
 */

public enum Mark {

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
     * @return Enum Mark
     * @exception IllegalArgumentException is thrwon if ordinal is invalid.
     */
    public static Mark valueOf (int ordinal) {
        switch (ordinal) {
            case 0: return BLANK;
            case 1: return Q;
            case 2: return INV;
            default:
                throw new IllegalArgumentException (String.format
                        ("Mark.valueOf(): ordinal = %d illegal", ordinal);
                )
        }
    }

}