import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class ReporterProxy implements LeakerListener {

    private DatagramSocket mailbox;
    private SocketAddress destination;
    private String pubFileName;

    /**
     *
     * @param mailbox
     * @param destination
     * @param pubFileName
     */
    public ReporterProxy (DatagramSocket mailbox, SocketAddress destination, String pubFileName) {
        this.mailbox = mailbox;
        this.destination = destination;
        this.pubFileName = pubFileName;
    }

    /**
     *
     * @throws IOException
     */
    public void report(String message) throws IOException {

        File pubFile = new File(pubFileName);
        Scanner sn = new Scanner(pubFile);

        // read the exponent and n into BigInteger
        BigInteger exp = new BigInteger(sn.nextLine());
        BigInteger n = new BigInteger(sn.nextLine());

        OAEP oaep = new OAEP();
        byte[] seed = new byte[32];


        BigInteger plaintext = oaep.encode(message, seed);

        BigInteger encoded = plaintext.modPow(exp, n); // RSA encoded
        byte[] payload = encoded.toByteArray();


        mailbox.send(new DatagramPacket(payload, payload.length, destination));

    }





}
