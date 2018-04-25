// File: LeakerProxy.java
// Unit: class LeakerProxy

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * LeakerProxy provides network proxy for Leaker objects. It is created in the Reporter program
 * and communicate with the Leaker.
 * @author Kyaw Khant Nyar
 */
public class LeakerProxy {

    // private data
    private LeakerListener listener;
    private DatagramSocket mailbox;
    private final int maxSize = 260;


    /**
     * constructor
     * @param mailbox datagram socket
     */
    public LeakerProxy(DatagramSocket mailbox) {

        this.mailbox = mailbox;
    }

    /**
     * set listener and run the thread
     * @param listener the LeakerListener object
     */
    public void setListener(LeakerListener listener) {
        this.listener = listener;
        new ReaderThread().start();
    }

    /**
     * the thread, receives the DatagramPacket, figure out the actual payload size
     * and send the payload to the listener to print out the secret message
     */
    private class ReaderThread extends Thread {
        public void run() {
            byte[] payload = new byte[maxSize];

            try {
                for (;;) {

                    DatagramPacket secretPacket = new DatagramPacket(payload, payload.length);
                    mailbox.receive(secretPacket);

                    DataInputStream in = new DataInputStream(new ByteArrayInputStream
                            (payload, 0, secretPacket.getLength()));

                    int size = in.read(payload); // get actual payload size

                    byte[] actual = new byte[size];

                    for (int i = 0; i < size; i++) {
                        actual[i] = payload[i];
                    }
                    listener.report(actual);


                }
            } catch (IOException exc) {
                exc.printStackTrace(System.err);
                System.exit(1);
            }


        }
    }


}
