// File: ViewProxy.java
//

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ViewProxy implements ModelListener {


    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ViewListener listener;


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


    public void setListener(ViewListener listener) {
        this.listener = listener;
        new ReaderThread() .start();
    }

    public void newGame() {

        try {
            out.writeByte('N');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

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


    public void waitingForOther(){

        try {
            out.writeByte('O');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    public void yourTurn(){

        try{
            out.writeByte('T');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    public void otherTurn(String name){

        try {
            out.writeByte('U');
            out.writeUTF(name);
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    public void youWin(){
        try {
            out.writeByte('W');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    public void otherWin(String name){

        try {
            out.writeByte('X');
            out.writeUTF(name);
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    public void quit(){

        try {
            out.writeByte('Q');
            out.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }


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


    private static void displayError(String msg) {
        System.err.printf("ViewProxy: %s%n", msg);
        System.exit(1);
    }

    private static void displayError(IOException exc) {
        System.err.println("ViewProxy: I/O displayError");
        exc.printStackTrace(System.err);
        System.exit(1);

    }
}