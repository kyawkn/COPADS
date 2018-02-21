/**
 * File: Getter.java
 * Author: Kyaw Khant Nyar (kxk3035@rit.edu)
 * Getter is the runnable object that gets the computed values from the
 * monitor and outputs them.
 */
public class Getter implements Runnable {

    private QuadraticMonitor monitor;
    private int max;

    /**
     * constructor getter
     */
    public Getter(QuadraticMonitor monitor, int val) {
        this.monitor = monitor;
        this.max = val;
    }

    // run
    public void run() {
        try {
            for (int x = 0; x <= max; x++) {
                int fVal = monitor.getF(x);
                int gVal = monitor.getG(x);
                int hVal = monitor.getH();

                System.out.println(x + "\t" + hVal + "\t" + gVal + "\t" + fVal);
            }

        } catch (Exception x){
            System.err.println("Error in getter: ");
            System.exit(1);
        }
    }
}
