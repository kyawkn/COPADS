// file ModelProxy.java
// Unit Class ModelProxy

// *********************************************************************************
// This file uses ModelProxy.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// **********************************************************************************

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Class ModelProxy provides the model proxy for the SixQueens game. It implements the client
 * side of the client-server network communication.
 *
 * @author Kyaw Khant Nyar
 * @version 4-April-2018
 */
public class ModelProxy implements ViewListener {

    // private data members
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    private ModelListener listener;


    /**
     * Construct a new model proxy
     * @param socket Socket
     * @exception IOException gets thrown
     *      if an I/O error occurred
     */
    public ModelProxy(Socket socket) {
        try {
            this.socket = socket;
            socket.setTcpNoDelay(true);
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    // public operations

    /**
     * Set the model listener object for the model proxy
     * @param listener Model listener
     */
    public void setListener(ModelListener listener) {
        this.listener = listener;
        new ReaderThread().start();

    }

    /**
     * Report that a player joined the game
     * @param view View object making the report
     * @param name Player's name
     */
    public void join(ModelListener view, String name){
        try {
            output.writeByte('J');
            output.writeUTF(name);
            output.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that a queen is placed in a square
     * @param view view object making the report
     * @param row queen square row pos
     * @param col queen square col pos
     */
    public void chosenQueen(ModelListener view, int row, int col){
        try {
            output.writeByte('S');
            output.writeByte(row);
            output.writeByte(col);
            output.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * report that a new game was requested
     * @param view view object making the report
     */
    public void newGame(ModelListener view){
        try{
            output.writeByte('G');
            output.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * report that the player quit.
     * @param view view object making the report
     */
    public void quit(ModelListener view) {
        try{
            output.writeByte('Q');
            output.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }



    /**
     * class ReaderThread receives message from the network, decodes them
     * and call the proper methods to process them
     */
    private class ReaderThread extends Thread {

        /**
         * Run the reader thread
         */
        public void run() {
            int op, row, col;
            String name;


            try {
                while(true) {
                    op = input.readByte();
                    switch (op) {
                        case 'N':
                            listener.newGame();
                            break;
                        case 'M':
                            row = input.readByte();
                            col = input.readByte();
                            listener.setQueenMark(row, col);

                            break;
                        case 'O':
                            listener.waitingForOther();
                            break;
                        case 'T':
                            listener.yourTurn();
                            break;
                        case 'U':
                            name = input.readUTF();
                            listener.otherTurn(name);
                            break;
                        case 'W':
                            listener.youWin();
                            break;
                        case 'X':
                            name = input.readUTF();
                            listener.otherWin(name);
                            break;
                        case 'Q':
                            listener.quit();
                            break;
                            default:
                                displayError("Message Error at ModelProxy");
                                break;
                    }

                }
            } catch (EOFException exc) {
            } catch (IOException exc) {
                displayError(exc);
            } finally {
                try {
                    socket.close();
                } catch (IOException exc) {
                }
            }
        }
    }

    // private hidden operations

    /**
     * Print an error message and exit the program
     * @param msg Message
     */
    private static void displayError(String msg) {
        System.err.printf("ModelProxy: %s%n", msg);
        System.exit(1);
    }

    /**
     * Print an I/O error message and exit the program
     * @param exc exception
     */
    private static void displayError(IOException exc) {
        System.err.println("I/O error in ModelProxy: ");
        exc.printStackTrace(System.err);
        System.exit(1);

    }
}