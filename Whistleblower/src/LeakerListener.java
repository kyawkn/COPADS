import java.io.IOException;

public interface LeakerListener {

    /**
     *
     * @param message
     * @throws IOException
     */
    public void report(String message) throws IOException;
}
