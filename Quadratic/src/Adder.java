/**
 * file: Adder.java
 * author: Kyaw Khant Nyar (kxk3035@rit.edu)
 * Class Adder is the runnable object that adds up the two values
 * and save them in their respective arrays.
 */

public class Adder implements Runnable {

    private QuadraticMonitor monitor;
    private int index;
    private int addType;

    /**
     * constructor
     * @param monitor QuadraticMonitor object
     * @param index current index where the calculation is happening
     * @param addType an int representing the addition types 1 for F, 1 for G
     */

    public Adder(QuadraticMonitor monitor, int index, int addType) {

        this.monitor = monitor;
        this.index = index;
        this.addType = addType;
    }


    /**
     * execute the runnable
     */
    public void run() {
        try {
            if (addType == 1) {
                monitor.putF(index, monitor.getF(index - 1) + monitor.getG(index - 1));
            } else {
                monitor.putG(index,monitor.getG(index - 1) + monitor.getH());
            }
        } catch (Exception ex) {
            System.err.println("Interrupted while adding: " + ex);
        }

    }

}
