// File: ReporterProxy.java
// Unit: Class ReporterProxy


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;


/**
 * ReporterProxy provides network proxy for Reporter objects. It is created in the Leaker program
 * and communicate with the Reporter.
 * @author Kyaw Khant Nyar
 */
public class ReporterProxy implements LeakerListener {

    private DatagramSocket mailbox;
    private SocketAddress destination;

    /**
     * constructor
     * @param mailbox DatagramSocket
     * @param destination SocketAddress
     */
    public ReporterProxy (DatagramSocket mailbox, SocketAddress destination) {
        this.mailbox = mailbox;
        this.destination = destination;
    }

    /**
     * report sends the encrypted byte array message to the Leaker
     * @throws IOException
     */
    public void report(byte[] payload) throws IOException {

        mailbox.send(new DatagramPacket(payload, payload.length, destination));

    }





}
