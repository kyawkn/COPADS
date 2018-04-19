// Fill: LeakerModel.java
// Unit: LeakerModel Class
/**
 * @author: Kyaw Khant Nyar (kxk3035@rit.edu)
 */

import java.io.IOException;

public class LeakerModel {

    private LeakerListener listener;


    public LeakerModel (LeakerListener listener) {
        this.listener = listener;
    }


    public void leak(String message) {
        try {
            listener.report(message);
        } catch (IOException exc) {
            exc.printStackTrace(System.err);
            System.exit(1);
        }
    }


}
