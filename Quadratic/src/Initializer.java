/**
 * File: Initializer.java
 * Author: Kyaw Khant Nyar (kxk3035@rit.edu)
 * Initializer is the runnable object that initialize the program
 * with the inputs from the commandline
 */
public class Initializer implements Runnable {

    private QuadraticMonitor monitor;
    private int a;
    private int b;
    private int c;

    /**
     * constructor
     * @param monitor the monitor object
     * @param a coefficient value a
     * @param b coefficient value b
     * @param c coefficient value c
     */
    public Initializer(QuadraticMonitor monitor, int a, int b, int c) {
        this.monitor = monitor;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // run the thread
    public void run() {
        try {
            {
                // initial values
                monitor.putH( 2 * a);
                monitor.putF(0, c);
                monitor.putG(0, a+b);

            }
        } catch (Exception ex){

            System.err.println("Error while Initializing: " + ex);
            System.exit(1);
        }
    }
}
