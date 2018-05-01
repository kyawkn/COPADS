// Fill: LeakerModel.java
// Unit: LeakerModel Class

import java.io.File;

/**
 * Leaker model provides the application logic for the Leaker
 * @author Kyaw Khant Nyar
 */
public class LeakerModel {

    // private data
    private LeakerListener listener;

    /**
     * constructor
     * @param listener listener object
     */
    public LeakerModel (LeakerListener listener) {
        this.listener = listener;
    }


    /**
     * Encode the message into byte array using RSA key cryptography
     * @param message secret message
     * @param pubFileName public key file name
     */
    public void leak(String message, String pubFileName) {
        try {
            // read public key file
            RSA rsa = new RSA();
            rsa.setKeys(pubFileName);

            byte[] payload = rsa.encode(message);
            listener.report(payload);

        } catch (Exception exc) {
            exc.printStackTrace(System.err);
            System.exit(1);
        }
    }


}
