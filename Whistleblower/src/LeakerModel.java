// Fill: LeakerModel.java
// Unit: LeakerModel Class

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

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
            File pubFile = new File(pubFileName);
            Scanner sn = new Scanner(pubFile);

            // read the exponent and n into BigInteger
            BigInteger exp = new BigInteger(sn.nextLine());
            BigInteger n = new BigInteger(sn.nextLine());

            OAEP oaep = new OAEP();
            byte[] seed = new byte[32];

            // OAPE encode
            BigInteger plaintext = oaep.encode(message, seed);

            BigInteger encoded = plaintext.modPow(exp, n); // RSA encoded
            byte[] payload = encoded.toByteArray();
            listener.report(payload);

        } catch (IOException exc) {
            exc.printStackTrace(System.err);
            System.exit(1);
        }
    }


}
