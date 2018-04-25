// File: Leaker.java
// Unit: Class Leaker


import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Reporter takes in the secret message from the console, and then encrypt it using RSA
 * then the message is converted into byte array and send it as a UDP Datagram to the
 * Reporter.
 * @author Kyaw Khant Nyar
 */
public class Leaker {

    /**
     * constructor
     */
    public Leaker(){

    }

    /**
     * the main method
     * @param args commandline arguments
     */
    public static void main(String[] args) {

        try {
            if (args.length != 6)
                printUsage();

            String rhost = args[0];
            int rport = parseInt(args[1], "<rport>");
            String lhost = args[2];
            int lport = parseInt(args[3], "<lport>");
            String pubFileName = args[4];
            String message = args[5];

            DatagramSocket mailbox = new DatagramSocket(new InetSocketAddress(lhost, lport));

            ReporterProxy proxy = new ReporterProxy(mailbox, new InetSocketAddress(rhost, rport));

            LeakerModel model = new LeakerModel(proxy);
            model.leak(message, pubFileName);


        } catch (Exception exc) {
           printExceptionStack(exc);
        }


    }

    // private methods

    /**
     * parse the input string into integer
     * @param val the value to parse
     * @param label the label of the value
     * @return
     */
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
        System.err.println("Leaker Error");
        exc.printStackTrace(System.err);
        System.exit(1);
    }

    /**
     *
     * @param error
     */
    private static void printUsageError(String error) {
        System.err.printf("Leaker: %s%n", error);
        printUsage();
    }

    /**
     *
     */
    private static void printUsage(){
        System.err.println("Usage: java Leaker <rhost> <rport> <lhost> <lport> <publickeyfile> <message>\n");
        System.exit(1); // exit
    }




}
