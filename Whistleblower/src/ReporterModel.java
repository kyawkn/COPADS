// File: ReporterModel.java
// Unit: Class Reporter Model
//

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Reporter model provides the application logic for the Reporter
 * @author Kyaw Khant Nyar
 */
public class ReporterModel implements LeakerListener{


    // private data
    private File privateFile;

    /*
     * Reporter constructor
     */
    public ReporterModel(File privateFileName) {
        this.privateFile = privateFileName;
    }

    /**
     * takes in the bytearray payload, decrypt it using the private file
     * and reports it to the console.
     * @param payload the decoded secret message
     */
    public void report (byte[] payload){

        try {
            RSA rsa = new RSA();
            rsa.setKeys(privateFile);
            System.out.println(rsa.decode(payload));
        } catch (Exception exc) {
            System.err.println("ERROR");
        }


    }


}
