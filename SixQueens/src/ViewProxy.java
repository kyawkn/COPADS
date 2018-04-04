// File: ViewProxy.java
// Unit: Class View Proxy
// ******************************************************************************
// This file uses ViewProxy.java file from Tic-Tac-Toe game as a reference and
// add appropriate modifications to work with SixQueens Game
// Original Author @Alan Kaminsky at ark@cs.rit.edu
// Original file can be found at https://www.cs.rit.edu/~ark/251/module08/notes.shtml
// ******************************************************************************
//
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Class ViewProxy provides the view proxy for the SixQueens game.
 * It implements the server side of the client-server network
 * communication.
 *
 * @author Kyaw Khant Nyar
 * @version 3-April-2018
 */
public class ViewProxy implements ModelListener {


    // private hidden data members
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ViewListener listener;


// Exported constructors

    /**
     * Construct a new view proxy
     * @param socket Socket
     *
     * @exception IOException
     *      Thrown if an I/O error occurred.
     */
    public ViewProxy(Socket socket) {

        try {
            this.socket = socket;
            socket.setTcpNoDelay(true);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException exc) {
            displayError(exc);
        }

    }

    /**
     * Set the view listener object for th view proxy
     * @param listener Model Listener
     */
    public void setListener(ViewListener listener) {
        this.listener = listener;
        new ReaderThread() .start();
    }

    /**
     * Report that a new game was started.
     */
    public void newGame() {

        try {
            out.writeByte('N');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that a queen was placed on a square.
     * @param row row position
     * @param col col position
     */
    public void setQueenMark(int row, int col) {

        try {
            out.writeByte('M');
            out.writeByte(row);
            out.writeByte(col);
            out.flush();
        } catch (IOException exc)
        {
            displayError(exc);
        }
    }

    /**
     * Report that the player is waiting for a partner
     */
    public void waitingForOther(){

        try {
            out.writeByte('O');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that it's the player's turn
     */
    public void yourTurn(){

        try{
            out.writeByte('T');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that it's the other player's turn
     * @param name other player's name
     */
    public void otherTurn(String name){

        try {
            out.writeByte('U');
            out.writeUTF(name);
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that the player has won
     */
    public void youWin(){
        try {
            out.writeByte('W');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that the other player has won
     * @param name the other player's name
     */
    public void otherWin(String name){

        try {
            out.writeByte('X');
            out.writeUTF(name);
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    /**
     * Report that a player quits.
     */
    public void quit(){

        try {
            out.writeByte('Q');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }


    /**
     * Class ReaderThread receives message from the network, decodes them
     * invokes the proper methods to process them.
     */
    private class ReaderThread extends Thread {

        public void run() {
            int op, row, col;
            String name;
            try {
                while(true) {
                    op = in.readByte();

                    switch (op)
                    {
                        case 'J':
                            name = in.readUTF();
                            listener.join(ViewProxy.this, name);
                            break;
                        case 'S':
                            row = in.readByte();
                            col = in.readByte();
                            listener.chosenQueen(ViewProxy.this, row, col);
                            break;
                        case 'G':
                            listener.newGame(ViewProxy.this);
                            break;
                        case 'Q':
                            listener.quit(ViewProxy.this);
                            break;
                            default:
                                displayError("Error Message");
                                break;
                    }
                }
            } catch (EOFException exc){
            } catch (IOException exc){
                displayError(exc);
            } finally {
                try {
                    socket.close();
                } catch (IOException exc) {
                }
            }
        }
    }


    // Hidden operations

    /**
     * Print an error message and terminate the program
     * @param msg Message.
     */
    private static void displayError(String msg) {
        System.err.printf("ViewProxy: %s%n", msg);
        System.exit(1);
    }

    /**
     * Print an I/O error message and terminate the program
     * @param exc Exception.
     */
    private static void displayError(IOException exc) {
        System.err.println("ViewProxy: I/O displayError");
        exc.printStackTrace(System.err);
        System.exit(1);

    }
}