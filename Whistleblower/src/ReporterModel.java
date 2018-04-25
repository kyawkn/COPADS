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
    private String privateFileName;

    /*
     * Reporter constructor
     */
    public ReporterModel(String privateFileName) {
        this.privateFileName = privateFileName;
    }

    /**
     * takes in the bytearray payload, decrypt it using the private file
     * and reports it to the console.
     * @param payload the decoded secret message
     */
    public void report (byte[] payload) throws IOException{


        // get private keys
        OAEP oaep = new OAEP();
        File privateFile = new File(privateFileName);
        Scanner sn = new Scanner(privateFile);
        BigInteger exp = new BigInteger(sn.nextLine());
        BigInteger n = new BigInteger(sn.nextLine());

        BigInteger secretBI = new BigInteger(payload);


        BigInteger plainText = secretBI.modPow(exp, n); //  c^d (mod n)

        String message = oaep.decode(plainText);

        System.out.println(message);

    }


}
