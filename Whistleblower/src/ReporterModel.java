// File: ReporterModel.java
// Unit: Class Reporter Model
//

import java.io.IOException;

/**
 * Reporter model provides the application logic for the Reporter
 * @author Kyaw Khant Nyar
 */
public class ReporterModel implements LeakerListener {


    // private data
    private RSA rsa;

    /*
     * Reporter constructor
     */
    public ReporterModel(String privateFileName) throws IOException {
        this.rsa = new RSA();
        rsa.setKeys(privateFileName);
    }

    /**
     * takes in the byte array payload, decrypt it using the private file
     * and reports it to the console.
     * @param payload the decoded secret message
     */
    public void report (byte[] payload){

        try {
            System.out.println(rsa.decode(payload));
        } catch (Exception exc) {
            System.err.println("ERROR");
        }

    }


}
