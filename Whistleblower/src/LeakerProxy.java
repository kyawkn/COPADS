// File: LeakerProxy.java
// Unit: class LeakerProxy


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class LeakerProxy {

    // private data
    private LeakerListener listener;
    private DatagramSocket mailbox;
    private String privateFileName;

    public LeakerProxy(DatagramSocket mailbox, String privateFileName) {

        this.mailbox = mailbox;
        this.privateFileName = privateFileName;

    }

    public void setListener(LeakerListener listener) {
        this.listener = listener;
        new ReaderThread().start();
    }

    private class ReaderThread extends Thread {
        public void run() {
            byte[] payload = new byte[260];

            try {
                for (;;) {

                    OAEP oaep = new OAEP();
                    DatagramPacket secretPacket = new DatagramPacket(payload, payload.length);
                    mailbox.receive(secretPacket);

                    DataInputStream in = new DataInputStream(new ByteArrayInputStream
                            (payload, 0, secretPacket.getLength()));

                    int size = in.read(payload);

                    byte[] actual = new byte[size];

                    for (int i = 0; i < size; i++) {
                        actual[i] = payload[i];
                    }


                    // get private keys
                    File privateFile = new File(privateFileName);
                    Scanner sn = new Scanner(privateFile);
                    BigInteger exp = new BigInteger(sn.nextLine());
                    BigInteger n = new BigInteger(sn.nextLine());

                    BigInteger secretBI = new BigInteger(actual);


                    BigInteger plainText = secretBI.modPow(exp, n); //  c^d (mod n)

                    String message = oaep.decode(plainText);

                    listener.report(message);


                }
            } catch (IOException exc) {
                exc.printStackTrace(System.err);
                System.exit(1);
            }


        }
    }


}
