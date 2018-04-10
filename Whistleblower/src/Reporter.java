// File: Reporter.java
// Unit: Class Reporter

import java.io.File;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * TODO// Description
 */
public class Reporter {

    /**
     * constructor
     */
    public Reporter(){

    }

    /**
     * main method
     * @param args commandline arguments
     */
    public static void main(String[] args) {

        try {
            if (args.length != 3) printUsage();

            String rhost = args[0];
            int rport = parseInt(args[1], "<rport>");
            String privateFileName = args[2];

            File privateFile = new File(privateFileName);
            Scanner sn = new Scanner(privateFile);

            BigInteger exp = new BigInteger(sn.nextLine());
            BigInteger n = new BigInteger(sn.nextLine());


            DatagramSocket mailbox = new DatagramSocket(new InetSocketAddress(rhost, rport));


            while (true) {
                byte[] payload = new byte[256];
                OAEP oaep = new OAEP();
                DatagramPacket secretPacket = new DatagramPacket(payload, payload.length);
                mailbox.receive(secretPacket);


                BigInteger secretBI = new BigInteger(payload);
                System.out.println(secretBI);
                System.out.println();
                BigInteger plainText = secretBI.modPow(exp, n); //  c^d (mod n)
                System.out.println(plainText);
                String message = oaep.decode(plainText);

                System.out.println(message);

            }

        } catch (Exception exc){
            printExceptionStack(exc);
        }
    }

    // private methods
    private static int parseInt(String val, String label) {
        int i = 0;

        try {
            i = Integer.parseInt(val);
        } catch (NumberFormatException exc) {
            printUsageError(String.format("illegal %s = \"%s\"", label, val));
        }
        return i;
    }

    /**
     * Print an I/O error message and terminate
     * @param exc Exception
     */
    private static void printExceptionStack(Exception exc) {
        System.err.println("Reporter Error");
        exc.printStackTrace(System.err);
        System.exit(1);
    }

    private static void printUsageError(String error) {
        System.err.printf("Reporter: %s%n", error);
        printUsage();
    }

    private static void printUsage(){
        System.err.println("Usage: java Reporter <rhost> <rport> <privatekeyfile>\n");
        System.exit(1); // exit
    }
}
