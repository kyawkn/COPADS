// File: LeakerListener.java
// Unit: Interface LeakerListener

import java.io.IOException;

/**
 * LeakerListener specifies the interface to an object that receives message from the
 * Leaker.
 * @author Kyaw Khant Nyar
 */
public interface LeakerListener {

    /**
     * report the message in a byte array
     * @param payload byte array
     * @throws IOException
     */
    public void report(byte[] payload) throws IOException;
}
