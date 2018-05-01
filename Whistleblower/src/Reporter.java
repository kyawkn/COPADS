// File: Reporter.java
// Unit: Class Reporter

// imports
import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;



/**
 * Reporter receives UDP datagram from the Leaker and then it decrypts the datagram and prints
 * the message to the console
 * @author Kyaw Khant Nyar
 */
public class Reporter {

    /**
     * constructor
     */
    public Reporter() {

    }

    /**
     * main method
     *
     * @param args commandline arguments
     */
    public static void main(String[] args){


        try {

            if (args.length != 3) printUsage();

            String rhost = args[0];
            int rport = parseInt(args[1], "<rport>");
            String privateFileName = args[2];


            DatagramSocket mailbox = new DatagramSocket(new InetSocketAddress(rhost, rport));

            LeakerProxy proxy = new LeakerProxy(mailbox);
            ReporterModel model = new ReporterModel(privateFileName);
            proxy.setListener(model);
        } catch (IOException exc) {
            exc.printStackTrace(System.err);
            System.exit(1);
        }
    }

    // private methods

    /**
     * parse the string into an int
     * @param val the desired string to parse
     * @param label the label of the value
     * @return parsed integer
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
     * display error message to the system error
     * @param error error message string
     */
    private static void printUsageError(String error) {
        System.err.printf("Reporter: %s%n", error);
        printUsage();
    }

    /**
     * display usage to the System error and exits
     */
    private static void printUsage() {
        System.err.println("Usage: java Reporter <rhost> <rport> <privatekeyfile>\n");
        System.exit(1); // exit
    }
}
