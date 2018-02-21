/**
 * file: QuadraticMonitor.java
 * author: Kyaw Khant Nyar (kxk3035@rit.edu)
 * This is the monitor class that holds the value for F(x), G(x) and H.
 *
 */
public class QuadraticMonitor {

    private Integer[] fArray;
    private Integer[] gArray;
    private Integer hValue;
    private Integer max;

    // constructor
    public QuadraticMonitor(int max) {
        this.fArray = new Integer[max+1];
        this.gArray = new Integer[max+1];
        this.max = max;

    }

    /**
     * this method puts the given value into the monitor.
     * @param x current x
     * @param value F(x)
     * @throws InterruptedException
     */

    // changed to terminate the program when errors occur
    // TODO: check if this meets the requirements
    public synchronized void putF (int x, int value){

        // precondition checks

        if(x < 0 || x > (max)) {
            System.out.println("Invalid x at putF.");
            System.exit(1);
        }

        if (fArray[x] != null) {
            System.out.println("F(x) has already been put.");
            System.exit(1);
        }

        else {


            fArray[x] = value;
            notifyAll();

        }
    }

    /**
     * this method puts the given value into the monitor.
     * @param x current x
     * @param value G(x)
     * @throws InterruptedException
     */

    // changed to terminate the program when errors occur
    // TODO: check if this meets the requirements
    public synchronized void putG (int x, int value) {

        // precondition checks

        if(x < 0 || x > (max)) {
            System.out.println("Invalid x at putF.");
            System.exit(1);
        }

        if (gArray[x] != null) {
            System.out.println("F(x) has already been put.");
            System.exit(1);
        }
        else {

            gArray[x] = value;
            notifyAll();

        }
    }

    /**
     * this method puts the given value into the monitor.
     * @param value H
     * @throws InterruptedException
     */
    // changed to terminate the program when errors occur
    public synchronized void putH (int value){

        if(hValue != null) {
            System.out.println("H has already been put");
            System.exit(1);
        }
        else {
            hValue = value;
            notifyAll();
        }
    }

    /**
     * This returns the value of F(x)
     * @param x the desired x value to look up
     * @return the value of F(x)
     * @throws InterruptedException, ArrayIndexOutOfBoundsException
     */
    public synchronized int getF (int x) {


        if(x < 0 || x > (max)) {
            System.out.println("Invalid x at getF.");
            System.exit(1);
        }
        else {
            try {
                while (fArray[x] == null)
                    wait();

                int v = (fArray[x] != null) ? fArray[x] : 0;
                notifyAll();
                return v;
            } catch (InterruptedException | ArrayIndexOutOfBoundsException ex) {
                System.err.println("Error getting F(x): " + ex);
                System.exit(1);
            }
        }
        return 0;

    }

    /**
     * this returns the value of G(x)
     * @param x the desired x value to look up
     * @return the value of G(x)
     */
    public synchronized int getG (int x) {

        if(x < 0 || x > (max)) {
            System.out.println("Invalid x at getG.");
            System.exit(1);
        }
        else {
            try {
                while (gArray[x] == null)
                    wait();

                int v = (gArray[x] != null) ? gArray[x] : 0;
                notifyAll();
                return v;
            } catch (InterruptedException | ArrayIndexOutOfBoundsException ex) {
                System.err.println("Error getting G(x): " + ex);
                System.exit(1);
            }
        }
        return 0;
    }

    /**
     * This returns the value of H
      * @return the value of H
     * @throws Exception
     */
    // changed to terminate the program when errors occur
    public synchronized int getH(){

        try {
            while (hValue == null)
                wait();
            int v = (hValue != null) ? hValue : 0;
            notifyAll();
            return v;
        } catch (Exception ex) {
            System.out.println("Error getting H: " + ex);
            System.exit(1);
        }
        return 0;
    }
}
