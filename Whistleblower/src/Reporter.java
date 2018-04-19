// File: Reporter.java
// Unit: Class Reporter


import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * TODO// Description
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
    public static void main(String[] args) throws Exception {


        if (args.length != 3) printUsage();

        String rhost = args[0];
        int rport = parseInt(args[1], "<rport>");
        String privateFileName = args[2];


        DatagramSocket mailbox = new DatagramSocket(new InetSocketAddress(rhost, rport));

        LeakerProxy proxy = new LeakerProxy(mailbox, privateFileName);
        ReporterModel model = new ReporterModel();

        proxy.setListener(model);
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


    private static void printUsageError(String error) {
        System.err.printf("Reporter: %s%n", error);
        printUsage();
    }

    private static void printUsage() {
        System.err.println("Usage: java Reporter <rhost> <rport> <privatekeyfile>\n");
        System.exit(1); // exit
    }
}
