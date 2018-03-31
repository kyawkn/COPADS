import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SixQueens {

    public static void main (String[] args) {

        if (args.length != 3) displayUsage();
        String host = args[0];
        int port = makeInt(args[1], "<port>");
        String playerName = args[2];

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));

            SixQueensView view = SixQueensView.create (playerName);
            ModelProxy proxy = new ModelProxy(socket);
            view.setListener(proxy);
            proxy.setListener(view);

            proxy.join(view, playerName);

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

        System.err.println("java SixQueens <host> <port> <playername>");
        System.exit(1);
    }
}