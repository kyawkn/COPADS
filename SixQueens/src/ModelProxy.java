// file ModelProxy.java
// Unit Class ModelProxy


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ModelProxy implements ViewListener {

    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    private ModelListener listener;


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

    public void setListener(ModelListener listener) {
        this.listener = listener;
        new ReaderThread().start();

    }

    public void join(ModelListener view, String name){
        try {
            output.writeByte('J');
            output.writeUTF(name);
            output.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    //

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

    public void newGame(ModelListener view){
        try{
            output.writeByte('G');
            output.flush();
        } catch (IOException exc) {
            displayError(exc);
        }
    }

    public void quit(ModelListener view) {
        try{
            output.writeByte('Q');
            output.flush();
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

    private static void displayError(String msg) {
        System.err.printf("ModelProxy: %s%n", msg);
        System.exit(1);
    }

    private static void displayError(IOException exc) {
        System.err.println("I/O error in ModelProxy: ");
        exc.printStackTrace(System.err);
        System.exit(1);

    }
}