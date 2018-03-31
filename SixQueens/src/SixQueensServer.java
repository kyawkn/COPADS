import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SixQueensServer {

    public static void main (String[] args) {

        if (args.length != 2) displayUsage();
        String host = args[0];
        int port = makeInt(args[1], "<port>");

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind (new InetSocketAddress(host, port));

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


    private static int makeInt(String val, String lbl) {
        int i = 0;

        try {
            i = Integer.parseInt(val);
        } catch (NumberFormatException exc) {
            displayUsageError(String.format("illegal %s = \"%s\"", lbl, val));
        }
        return i;
    }

    private static void displayError(IOException exc) {
        System.err.println("SixQueens: I/O error");
        exc.printStackTrace(System.err);
        System.exit(1);
    }

    private static void displayUsageError(String err) {
        System.err.printf("SixQueens: %s%n", err);
        displayUsage();

    }

    private static void displayUsage() {

        System.err.println("java SixQueens <host> <port>");
        System.exit(1);
    }
}