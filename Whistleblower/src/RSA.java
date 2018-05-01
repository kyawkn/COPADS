// File: RSA.java
// Class RSA
// author@ Kyaw Khant Nyar
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * RSA class handles the RSA key crypto encoding and decoding
 */
public class RSA {

    // private data members
    private BigInteger exp;
    private BigInteger n;
    private OAEP oaep;

    /**
     * RSA constructor
     */
    public RSA(){
        this.oaep = new OAEP();
    }

    /**
     * get the keys from the file and set the keys for later use
     * @param file keyfile
     * @throws IOException
     */
    public void setKeys(File file) throws IOException{
        Scanner sn = new Scanner(file);
        this.exp = new BigInteger(sn.nextLine());
        this.n = new BigInteger(sn.nextLine());
    }

    /**
     * encodes the input string into byte array using OAEP encode
     * and RSA key cryptography
     * @param message input string
     * @return encoded byte array
     * @throws Exception
     */
    public byte[] encode(String message){

        byte[] seed = new byte[32];
        // OAPE encode
        BigInteger plaintext = oaep.encode(message, seed);
        BigInteger encoded = plaintext.modPow(exp, n); // RSA encoded
        return encoded.toByteArray();

    }

    /**
     * decodes the secret byte array using RSA key cryptography, and OAEP
     * @param secret secret byte array sent by the leaker
     * @return decoded secret message
     */
    public String decode(byte[] secret) {

        BigInteger secretBI = new BigInteger(secret);
        BigInteger plainTextBI = secretBI.modPow(exp, n); //  c^d (mod n)
        return oaep.decode(plainTextBI);
    }
}
