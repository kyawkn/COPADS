// File SixQueensServer.java
// Unit class SixQueensServer

// *********************************************************************************
// This file uses TicTacToeServer.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// **********************************************************************************

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SixQueensServer is the main server program for the SixQueens game
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */
public class SixQueensServer {

    /**
     * main program
     * @param args system arguments
     */
    public static void main (String[] args) {

        if (args.length != 2) displayUsage();
        String host = args[0];
        int port = makeInt(args[1], "<port>");

        try {

            // Listen for connections from clients
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind (new InetSocketAddress(host, port));

            // Session management
            SixQueensModel model = null;

            while (true){
                Socket socket = serverSocket.accept();
                ViewProxy proxy = new ViewProxy(socket);

                if (model == null || model.isGameOver()) {
                    model = new SixQueensModel();
                    proxy.setListener(model);
                } else {
                    proxy.setListener(model);
                    model = null;
                }
            }

        } catch (IOException exc) {
            displayError(exc);
        }

    }


    /**
     * Parse the string into an int
     * @param val string to cast into an int
     * @param lbl label
     * @return if no error occurs, return the int
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
     * Take in the exception and display it
     * @param exc Exception
     */
    private static void displayError(IOException exc) {
        System.err.println("SixQueens: I/O error");
        exc.printStackTrace(System.err);
        System.exit(1);
    }

    /**
     * Print error message and the usage
     * @param err Error message to display
     */
    private static void displayUsageError(String err) {
        System.err.printf("SixQueens: %s%n", err);
        displayUsage();

    }

    /**
     * Print usage for SixQueensServer
     */
    private static void displayUsage() {

        System.err.println("java SixQueensServer <host> <port>");
        System.exit(1);
    }
}