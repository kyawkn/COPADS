// file Mark.java
//
/**
 * Enum Queen enumrates the
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