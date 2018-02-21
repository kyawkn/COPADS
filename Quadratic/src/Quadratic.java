
/**
 * Assignment: CSCI 251 - Project 1
 * Author @ Kyaw Khant Nyar (kxk3035@rit.edu)
 * Class Quadratic is the main program of the quadratic project.
 */

public class Quadratic {

    public static void main(String[] args) throws Exception {

        if (args.length != 4) displayErr(1); // check the inputs

        else try {

            // inputs
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);


            if(max < 0) displayErr(2); // check the 4th value


            QuadraticMonitor monitor = new QuadraticMonitor(max);

            // get thread prints out the answer
            new Thread(new Getter(monitor, max)).start();

            // the add threads
            for(int i = 1; i <= max; i++) {
                new Thread(new Adder(monitor, i , 1)).start();
                new Thread(new Adder(monitor,i, 2)).start();
            }

            // initialize thread
            new Thread(new Initializer(monitor, a, b, c)).start();


        }
        //
        catch (Exception ex){
            System.err.println("Error: Invalid input types, each argument must be an integer(type int).");
            System.err.println("ErrorType: " + ex);
            displayErr(0);
        }

    }

    /**
     * displays errors and exit the program
     * @param errno error number
     */
    private static void displayErr(int errno){
        if(errno == 1) System.err.println("Error: Invalid number of arguments.");
        if(errno == 2) System.err.println("Error: <max> must be an integer >= 0");
        System.out.println("Usage: java Quadratic <a> <b> <c> <max>");
        System.exit(1);
    }

}
