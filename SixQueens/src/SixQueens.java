// File: SixQueens.java
// Unit: Class SixQueens

// *********************************************************************************
// This file uses TicTacToe.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// **********************************************************************************
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * SixQueens is the client program for the SixQueens Game
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */
public class SixQueens {

    /**
     * main program
     * @param args system arguments
     */
    public static void main (String[] args) {

        // process the command line arguments
        if (args.length != 3) displayUsage();
        String host = args[0];
        int port = makeInt(args[1], "<port>");
        String playerName = args[2];

        try {
            // socket connection to server
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));

            // setting up the view and model proxy
            SixQueensView view = SixQueensView.create (playerName);
            ModelProxy proxy = new ModelProxy(socket);
            view.setListener(proxy);
            proxy.setListener(view);

            // inform server that a player has joined
            proxy.join(view, playerName);

        } catch (IOException exc) {
            displayError(exc);
        }

    }


    /**
     * Parse an int from the string
     * @param val String to parse
     * @param lbl error message label
     * @return integer value
     */
    private static int makeInt(String val, String lbl) {
        int i = 0;

        try {
            i = Integer.parseInt(val);
        } catch (NumberFormatException exc) {
            displayUsageError(String.format("illegal %s = \"%s\"", lbl, val));
        }
        return i;
    }

    /**
     * Print an I/O error message and terminate
     * @param exc Exception
     */
    private static void displayError(IOException exc) {
        System.err.println("SixQueens: I/O error");
        exc.printStackTrace(System.err);
        System.exit(1);
    }

    /**
     * Print an error message, and the usage and exit
     * @param err erro message
     */
    private static void displayUsageError(String err) {
        System.err.printf("SixQueens: %s%n", err);
        displayUsage();

    }

    /**
     * Print a usage message and exit
     */
    private static void displayUsage() {

        System.err.println("java SixQueens <host> <port> <playername>");
        System.exit(1);
    }
}